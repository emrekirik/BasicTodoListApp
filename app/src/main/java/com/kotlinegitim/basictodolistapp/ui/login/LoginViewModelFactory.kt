package com.kotlinegitim.basictodolistapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlinegitim.basictodolistapp.data.repository.UserRepository
import com.kotlinegitim.basictodolistapp.data.room.UserDao

class LoginViewModelFactory(private val userDao: UserDao): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}