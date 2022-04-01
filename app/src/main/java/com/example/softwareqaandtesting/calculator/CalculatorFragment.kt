package com.example.softwareqaandtesting.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.softwareqaandtesting.Constants.Companion.LISTING_FRAGMENT_TAG
import com.example.softwareqaandtesting.R
import com.example.softwareqaandtesting.database.CalculationEntity
import com.example.softwareqaandtesting.databinding.FragmentCalculatorBinding
import com.example.softwareqaandtesting.listing.ListingFragment
import com.example.softwareqaandtesting.observeOnce


class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding: FragmentCalculatorBinding
        get() = _binding!!

    private lateinit var viewModel: CalculatorViewModel
    private var userId: Int? = null
    private var calculationType: CalculationType? = null
    private var number1: Double? = null
    private var number2: Double? = null
    private var resultNumber: Double? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntent()
        viewModel = ViewModelProvider(requireActivity())[CalculatorViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCalculatorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeResultLiveData()
        observeListLiveData()
    }

    private fun getIntent(){
        if(arguments != null){
            userId = arguments?.getInt(USER_ID,0)
        }
    }

    private fun observeResultLiveData() {
        viewModel.resultLiveData.observe(viewLifecycleOwner) { result ->
            binding.resultTextview.text = getString(R.string.result_with_number, result.toString())
            resultNumber = result
        }
    }

    private fun observeListLiveData(){
        viewModel.calculationListLiveData.observeOnce(viewLifecycleOwner) { list ->
            if (!list.isNullOrEmpty()) {
                activity?.supportFragmentManager?.let {
                    val frTransaction = it.beginTransaction()
                    frTransaction.replace(R.id.frame_layout, ListingFragment.newInstance(list))
                    frTransaction.addToBackStack(null)
                    frTransaction.commit()
                }
            }
        }
    }

    private fun setUpViews() {
        binding.sumButton.setOnClickListener {
            calculationType = CalculationType.SUM
            number1 = binding.firstNumber.text.toString().toDoubleOrNull()
            number2 = binding.secondNumber.text.toString().toDoubleOrNull()
            val sumOfNumbers = viewModel.sumOfNumbers(
                number1,
                number2
            )

            if (sumOfNumbers != null) {
                viewModel.updateResultLiveData(sumOfNumbers)
            } else binding.resultTextview.text = getString(R.string.try_again)
        }

        binding.minusButton.setOnClickListener {
            calculationType = CalculationType.MINUS
            number1 = binding.firstNumber.text.toString().toDoubleOrNull()
            number2 = binding.secondNumber.text.toString().toDoubleOrNull()
            calculationType = CalculationType.MINUS
            val minusOfNumbers = viewModel.minusOfNumbers(
                number1,
                number2
            )

            if (minusOfNumbers != null) {
                viewModel.updateResultLiveData(minusOfNumbers)
            } else binding.resultTextview.text = getString(R.string.try_again)
        }

        binding.multipleButton.setOnClickListener {
            calculationType = CalculationType.MULTIPLE
            number1 = binding.firstNumber.text.toString().toDoubleOrNull()
            number2 = binding.secondNumber.text.toString().toDoubleOrNull()
            val multipleOfNumbers = viewModel.multipleOfNumbers(
                number1,
                number2
            )

            if (multipleOfNumbers != null) {
                viewModel.updateResultLiveData(multipleOfNumbers)
            } else binding.resultTextview.text = getString(R.string.try_again)
        }

        binding.divideButton.setOnClickListener {
            calculationType = CalculationType.DIVIDE
            number1 = binding.firstNumber.text.toString().toDoubleOrNull()
            number2 = binding.secondNumber.text.toString().toDoubleOrNull()
            val divideOfNumbers = viewModel.divideOfNumbers(
                number1,
                number2
            )

            if(divideOfNumbers != null){
                viewModel.updateResultLiveData(divideOfNumbers)
            }else binding.resultTextview.text = getString(R.string.try_again)
        }

        binding.clearButton.setOnClickListener {
            calculationType = null
            number1 = null
            number2 = null
            binding.firstNumber.text.clear()
            binding.secondNumber.text.clear()
            binding.resultTextview.text = getString(R.string.result)

            binding.firstNumber.clearFocus()
            binding.secondNumber.clearFocus()
        }

        binding.saveButton.setOnClickListener {
            viewModel.addCalculation(
                CalculationEntity(
                    userId = userId ?: 0,
                    calculationType = calculationType?.name ?: " ",
                    firstNumber = number1 ?: 0.0,
                    secondNumber = number2 ?: 0.0
                )
            )
        }

        binding.listButton.setOnClickListener {
            if (userId != 0 && userId != null) {
                viewModel.getAllCalculations(userId ?: 0)
            } else {
                Toast.makeText(
                    requireContext(),
                    "You Dont Have Any Calculation To List",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.formatButton.setOnClickListener {
            val formattedResult = resultNumber?.let { it1 -> viewModel.formatResultNumber(it1) }
            formattedResult?.let {
                viewModel.updateResultLiveData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val USER_ID = "userId"

        fun newInstance(userId: Int): CalculatorFragment {
            val args = Bundle()
            args.putInt(USER_ID,userId)
            val fragment = CalculatorFragment()
            fragment.arguments = args
            return fragment
        }
    }

}