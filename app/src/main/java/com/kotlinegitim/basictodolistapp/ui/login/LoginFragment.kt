package com.kotlinegitim.basictodolistapp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kotlinegitim.basictodolistapp.R
import com.kotlinegitim.basictodolistapp.data.models.User
import com.kotlinegitim.basictodolistapp.data.repository.UserRepository
import com.kotlinegitim.basictodolistapp.data.room.AppDatabase
import com.kotlinegitim.basictodolistapp.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(AppDatabase.getInstance(requireContext()).userDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(layoutInflater)

        binding.SignUpButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener{
            val username = binding.userNameTextField.text.toString()
            val password = binding.passwordTextField.text.toString()
            val user = User(username = username, password = password)
            if (username.isNotEmpty() && password.isNotEmpty()){
                lifecycleScope.launch {
                    viewModel.login(user)
                }
                } else {
                binding.errorTextView.visibility = View.VISIBLE
                binding.errorTextView.text = "Kullanıcı adı veya şifre boş olamaz"
                }
        }

        viewModel.loginResult.observe(viewLifecycleOwner, Observer { success -> if(success){
            val action = LoginFragmentDirections.actionLoginFragmentToListFragment()
            NavHostFragment.findNavController(this).navigate(action)
        } else{
            binding.errorTextView.text = "Giriş başarısız. Lütfen kullanıcı adı ve şifrenizi kontrol edin."
        } })

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}