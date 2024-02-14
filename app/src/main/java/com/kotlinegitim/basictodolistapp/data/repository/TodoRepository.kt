package com.kotlinegitim.basictodolistapp.data.repository

import androidx.lifecycle.LiveData
import com.kotlinegitim.basictodolistapp.data.models.TodoItem
import com.kotlinegitim.basictodolistapp.data.room.TodoItemDao

class TodoRepository(private val todoDao: TodoItemDao) {
    // Tüm yapılacak işleri getir
    fun getAllTodos(): LiveData<MutableList<TodoItem>> {
        return todoDao.getAllTodoItems()
    }


    suspend fun insert(todo: TodoItem) {
        todoDao.insertTodoItem(todo)
    }


    suspend fun update(todo: TodoItem) {
        todoDao.update(todo)
    }


    suspend fun delete(todo: TodoItem) {
        todoDao.deleteTodoItem(todo)
    }
    suspend fun deleteAll(){
        todoDao.deleteAll()
    }
}