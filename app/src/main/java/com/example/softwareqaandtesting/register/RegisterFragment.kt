package com.example.softwareqaandtesting.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.softwareqaandtesting.Constants
import com.example.softwareqaandtesting.Constants.Companion.LOGIN_FRAGMENT_TAG
import com.example.softwareqaandtesting.R
import com.example.softwareqaandtesting.database.RegisterEntity
import com.example.softwareqaandtesting.databinding.FragmentRegisterBinding
import com.example.softwareqaandtesting.login.LoginFragment

class RegisterFragment : Fragment() {
    private lateinit var viewModel: RegisterViewModel
    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding
        get() = _binding!!
    private var registerError = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpObservers()
    }

    private fun setUpViews() {
        loginButtonClicked()
        registerButtonClicked()
    }

    private fun setUpObservers() {
        viewModel.liveData.observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                activity?.supportFragmentManager?.let {
                    val fragmentTransaction = it.beginTransaction()
                    fragmentTransaction.replace(R.id.frame_layout, LoginFragment.newInstance())
                    fragmentTransaction.addToBackStack(LOGIN_FRAGMENT_TAG)
                    fragmentTransaction.commit()
                }
            }
        })
    }

    private fun registerButtonClicked() {
        binding.registerButton.setOnClickListener {
            validFields()
            if (!registerError) {
                val user = RegisterEntity(
                    firstName = binding.firstNameEdittext.text.toString(),
                    lastName = binding.lastNameEdittext.text.toString(),
                    password = binding.passwordEdittext.text.toString(),
                    email = binding.mailEdittext.text.toString()
                )
                viewModel.registerUser(user)
            } else {
                Toast.makeText(
                    requireContext(),
                    "KARDEŞ BİLGİLERİNİ KONTROL ET",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun validFields(){
        if(!viewModel.isValidName(binding.firstNameEdittext.text)){
            registerError = true
            return
        }
        if(!viewModel.isValidName(binding.lastNameEdittext.text)){
            registerError = true
            return
        }
        if(!viewModel.isValidEmail(binding.mailEdittext.text)){
            registerError = true
            return
        }
        if (!viewModel.isValidPassword(binding.passwordEdittext.text) &&
            !viewModel.isValidPassword(binding.passwordEdittext.text) &&
            !viewModel.isPasswordsMatching(
                binding.passwordEdittext.text.toString(),
                binding.passwordEdittext.text.toString()
            )
        ) {
            registerError = true
            return
        }

    }

    private fun loginButtonClicked() {
        binding.loginButton.setOnClickListener {
            activity?.supportFragmentManager?.let {
                val fragmentTransaction = it.beginTransaction()
                fragmentTransaction.replace(R.id.frame_layout, LoginFragment.newInstance())
                fragmentTransaction.addToBackStack(Constants.LOGIN_FRAGMENT_TAG)
                fragmentTransaction.commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        fun newInstance(): RegisterFragment {
            val args = Bundle()
            val fragment = RegisterFragment()
            fragment.arguments = args
            return fragment
        }
    }
}