package com.kotlinegitim.basictodolistapp.ui.add

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.kotlinegitim.basictodolistapp.data.repository.TodoRepository
import com.kotlinegitim.basictodolistapp.ui.list.ListFragmentViewModel

class TodoViewModelFactory(private val application: Application): ViewModelProvider.Factory {
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListFragmentViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}