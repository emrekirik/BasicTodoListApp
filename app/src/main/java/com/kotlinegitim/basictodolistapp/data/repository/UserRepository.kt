package com.kotlinegitim.basictodolistapp.data.repository

import com.kotlinegitim.basictodolistapp.data.models.User
import com.kotlinegitim.basictodolistapp.data.room.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun login(username: String, password: String): Boolean{
        val user = userDao.getUserByUsername(username)
        return user?.password == password
    }

    suspend fun register(username: String, password: String): Boolean {
        val user = User(0, username, password)
        userDao.insert(user)
        return true
    }
}