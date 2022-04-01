package com.example.softwareqaandtesting.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.softwareqaandtesting.database.CalculationEntity
import com.example.softwareqaandtesting.databinding.FragmentListingBinding


class ListingFragment : Fragment() {
    private var _binding : FragmentListingBinding? = null
    private val binding: FragmentListingBinding
        get() = _binding!!
    private var calculationList: ArrayList<CalculationEntity>? = null

    private lateinit var adapter: ListingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListingBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getIntents()
        initAdapter()
    }

    private fun getIntents(){
        if(arguments != null){
            calculationList = arguments?.getParcelableArrayList(CALCULATION_LIST)
        }
    }

    private fun initAdapter(){
        adapter = ListingAdapter(calculationList?.toList() ?: listOf())
        binding.recyclerview.adapter = adapter
    }


    companion object {
        const val CALCULATION_LIST = "calculationList"
        fun newInstance(calculationList: ArrayList<CalculationEntity>): ListingFragment {
            val args = Bundle()
            args.putParcelableArrayList(CALCULATION_LIST, calculationList)
            val fragment = ListingFragment()
            fragment.arguments = args
            return fragment
        }
    }
}