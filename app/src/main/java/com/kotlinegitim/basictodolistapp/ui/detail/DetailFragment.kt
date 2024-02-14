package com.kotlinegitim.basictodolistapp.ui.detail

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kotlinegitim.basictodolistapp.R
import com.kotlinegitim.basictodolistapp.data.models.TodoItem
import com.kotlinegitim.basictodolistapp.databinding.FragmentDetailBinding
import com.kotlinegitim.basictodolistapp.ui.add.TodoViewModelFactory
import com.kotlinegitim.basictodolistapp.ui.list.ListFragmentViewModel
import com.kotlinegitim.basictodolistapp.ui.list.TodoListAdapter


class  DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: ListFragmentViewModel by viewModels{
        TodoViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater)

        val args = DetailFragmentArgs.fromBundle(requireArguments())

        binding.apply {
            editTextKisiAd.setText(args.todoItem.title)
            buttonKaydet.setOnClickListener{
                if (TextUtils.isEmpty(editTextKisiAd.text)){
                    Toast.makeText(requireContext(),"Boş bırakmayın", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val todo_str = editTextKisiAd.text
                val todoItem = TodoItem(
                    args.todoItem.id,
                    todo_str.toString()
                )
                viewModel.update(todoItem)
                Toast.makeText(requireContext(), "Notunuz güncellendi", Toast.LENGTH_SHORT).show()
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListFragment())
            }
        }


        return binding.root
    }
}