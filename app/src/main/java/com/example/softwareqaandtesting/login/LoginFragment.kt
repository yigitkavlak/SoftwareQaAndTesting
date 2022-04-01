package com.example.softwareqaandtesting.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.softwareqaandtesting.Constants
import com.example.softwareqaandtesting.R
import com.example.softwareqaandtesting.calculator.CalculatorFragment
import com.example.softwareqaandtesting.databinding.FragmentLoginBinding
import kotlin.math.log


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!
    private lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment with view binding
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        setUpViews()
        setUpObservers()
    }

    private fun setUpViews(){
        loginButtonClicked()
    }

    private fun setUpObservers(){
        viewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            if(user != null){
                activity?.supportFragmentManager?.let {
                    val fragmentTransaction = it.beginTransaction()
                    fragmentTransaction.replace(R.id.frame_layout, CalculatorFragment.newInstance(user.userId))
                    fragmentTransaction.addToBackStack(Constants.CALCULATOR_FRAGMENT_TAG)
                    fragmentTransaction.commit()
                }
            }
        }
    }

    private fun loginButtonClicked() {
        val email = binding.mailEdittext.text
        val password = binding.passwordEdittext.text
        binding.loginBtn.setOnClickListener {
            if (
                viewModel.isValidEmail(email) &&
                viewModel.isValidPassword(password)
            ) {
                viewModel.loginButtonClicked(email = email.toString(), password = password.toString())
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please use controls of your email and password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        fun newInstance(): LoginFragment {
            val args = Bundle()
            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }
    }

}