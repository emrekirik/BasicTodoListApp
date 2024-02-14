package com.kotlinegitim.basictodolistapp.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.kotlinegitim.basictodolistapp.R
import com.kotlinegitim.basictodolistapp.data.repository.UserRepository
import com.kotlinegitim.basictodolistapp.data.room.AppDatabase
import com.kotlinegitim.basictodolistapp.databinding.FragmentLoginBinding
import com.kotlinegitim.basictodolistapp.databinding.FragmentRegisterBinding
import com.kotlinegitim.basictodolistapp.ui.login.LoginFragmentDirections
import com.kotlinegitim.basictodolistapp.ui.login.LoginViewModel
import com.kotlinegitim.basictodolistapp.ui.login.LoginViewModelFactory
import kotlinx.coroutines.launch


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(AppDatabase.getInstance(requireContext()).userDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {
            val username = binding.userNameTextField.text.toString()
            val password = binding.passwordTextField.text.toString()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                lifecycleScope.launch {
                    viewModel.register(username, password)
                }
            } else {
                binding.textViewError.visibility = View.VISIBLE
                binding.textViewError.text = "Kullanıcı adı veya şifre boş olamaz"
            }
        }
        viewModel.registerResult.observe(viewLifecycleOwner, Observer { success -> if (success){
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            NavHostFragment.findNavController(this).navigate(action)
        } else {
            binding.textViewError.text = "Kayıt başarısız. Lütfen tekrar deneyin."
        }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}