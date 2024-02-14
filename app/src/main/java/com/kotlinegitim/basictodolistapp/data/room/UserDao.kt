package com.kotlinegitim.basictodolistapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kotlinegitim.basictodolistapp.data.models.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?
}