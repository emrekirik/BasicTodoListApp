package com.kotlinegitim.basictodolistapp.ui.list

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kotlinegitim.basictodolistapp.data.models.TodoItem
import com.kotlinegitim.basictodolistapp.databinding.FragmentListBinding
import com.kotlinegitim.basictodolistapp.ui.add.TodoViewModelFactory


class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var todoListAdapter: TodoListAdapter
    private val viewModel: ListFragmentViewModel by viewModels{
        TodoViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToAddFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }

        val todoList = mutableListOf<TodoItem>()
        todoListAdapter = TodoListAdapter(todoList, TodoListAdapter.TodoClickListener { todoItem ->
            findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(todoItem))
        })

        binding.apply {
            rv.setHasFixedSize(true)
            rv.layoutManager = LinearLayoutManager(requireContext())
            rv.adapter = todoListAdapter
            lifecycleOwner = this@ListFragment
        }

        viewModel.getAllTodo.observe(viewLifecycleOwner){
            todoListAdapter.updateList(it)
        }

        actionDelete()

    }

    private fun actionDelete(){
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val todoItem = todoListAdapter.todoList[position]
                viewModel.delete(todoItem)

                Snackbar.make(binding.root, "Silindi", Snackbar.LENGTH_LONG).apply {
                    setAction("Geri al"){
                        viewModel.insert(todoItem)
                    }
                    show()
                }
            }

        }).attachToRecyclerView(binding.rv)
    }

    private fun deleteAllItem(){
        AlertDialog.Builder(requireContext())
            .setTitle("Hepsini Sil")
            .setMessage("Emin misiniz")
            .setPositiveButton("Evet"){ dialog, _ ->
                viewModel.deleteAll()
                dialog.dismiss()
            }.setNegativeButton("Hayır"){dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }
}