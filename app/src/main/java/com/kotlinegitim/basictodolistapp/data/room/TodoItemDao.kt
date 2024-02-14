package com.kotlinegitim.basictodolistapp.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kotlinegitim.basictodolistapp.data.models.TodoItem

@Dao
interface TodoItemDao {
    @Query("SELECT * FROM todo_items")
    fun getAllTodoItems(): LiveData<MutableList<TodoItem>>

    @Insert
    suspend fun insertTodoItem(todoItem: TodoItem)

    @Delete
    suspend fun deleteTodoItem(todoItem: TodoItem)

    @Update
    suspend fun update(todoItem: TodoItem)

    @Query("DELETE FROM todo_items")
    suspend fun deleteAll()
}