package com.kotlinegitim.basictodolistapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlinegitim.basictodolistapp.data.models.User
import com.kotlinegitim.basictodolistapp.data.repository.UserRepository
import com.kotlinegitim.basictodolistapp.data.room.UserDao

class LoginViewModel(private val userDao: UserDao): ViewModel() {
    private val userRepository = UserRepository(userDao)
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean>get() = _loginResult
    private val _registerResult = MutableLiveData<Boolean>()
    val registerResult: LiveData<Boolean>get() = _registerResult

    suspend fun login(user: User){
        val isLoggedIn = userRepository.login(user.username, user.password)
        _loginResult.value = isLoggedIn
    }
    suspend fun register(username: String, password: String){
        val isRegistered = userRepository.register(username, password)
        _registerResult.value = isRegistered
    }
}