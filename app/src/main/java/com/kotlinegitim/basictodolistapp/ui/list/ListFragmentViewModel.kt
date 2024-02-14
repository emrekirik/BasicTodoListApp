package com.kotlinegitim.basictodolistapp.ui.list

import androidx.lifecycle.LiveData
import android.app.Application
import androidx.lifecycle.ViewModel
import com.kotlinegitim.basictodolistapp.data.models.TodoItem
import com.kotlinegitim.basictodolistapp.data.repository.TodoRepository
import androidx.lifecycle.viewModelScope
import com.kotlinegitim.basictodolistapp.data.room.AppDatabase
import kotlinx.coroutines.launch

class ListFragmentViewModel(application: Application) :
    ViewModel() {

    private val toDoItemDao = AppDatabase.getInstance(application).todoItemDao()
    private val repository: TodoRepository

    val getAllTodo: LiveData<MutableList<TodoItem>>

    init {
        repository = TodoRepository(toDoItemDao)
        getAllTodo = repository.getAllTodos()
    }

    fun delete(todo: TodoItem) {
        viewModelScope.launch() {
           repository.delete(todo)
        }
    }

    fun insert(todo: TodoItem) {
        viewModelScope.launch {
            repository.insert(todo)
        }
    }
    fun update(todo: TodoItem) {
        viewModelScope.launch {
          repository.update(todo)
        }
    }
    fun deleteAll(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}