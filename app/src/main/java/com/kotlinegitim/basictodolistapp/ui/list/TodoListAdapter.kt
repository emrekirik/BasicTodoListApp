package com.kotlinegitim.basictodolistapp.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinegitim.basictodolistapp.data.models.TodoItem
import com.kotlinegitim.basictodolistapp.databinding.ListItemBinding

class TodoListAdapter(var todoList: MutableList<TodoItem>, val clickListener: TodoClickListener) :
    RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(private var binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todoItem: TodoItem, clickListener: TodoClickListener) {
            binding.textViewKisiBilgi.text = todoItem.title
            binding.todoItem= todoItem
            binding.clickListener = clickListener

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        todoList.getOrNull(position)?.let { holder.bind(it, clickListener) }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateList(newList: MutableList<TodoItem>) {
        todoList.clear()
        todoList.addAll(newList) // yeni listeyi ekleyin
        notifyDataSetChanged()
    }

    class TodoClickListener(val clickListener: (todoItem: TodoItem) -> Unit){
        fun onClick(todoItem: TodoItem) = clickListener(todoItem)
    }
}