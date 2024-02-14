package com.kotlinegitim.basictodolistapp.ui.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.kotlinegitim.basictodolistapp.data.models.TodoItem
import com.kotlinegitim.basictodolistapp.data.repository.TodoRepository
import com.kotlinegitim.basictodolistapp.data.room.AppDatabase
import com.kotlinegitim.basictodolistapp.databinding.FragmentAddBinding
import com.kotlinegitim.basictodolistapp.ui.list.ListFragmentViewModel
import com.kotlinegitim.basictodolistapp.ui.login.LoginViewModelFactory

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private val viewModel: ListFragmentViewModel by viewModels{
        TodoViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)

        binding.apply {
            buttonKaydet.setOnClickListener {
                if (TextUtils.isEmpty(editTextKisiAd.text)){
                    Toast.makeText(requireContext(), "Boş bıraktınız.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val title_str = editTextKisiAd.text.toString()
                val newItem = TodoItem(title = title_str)
                viewModel.insert(newItem)
                Toast.makeText(requireContext(), "Başarılı bir şekilde eklendi.", Toast.LENGTH_SHORT).show()
                findNavController(this@AddFragment).navigate(AddFragmentDirections.actionAddFragmentToListFragment())
            }
        }
        return binding.root
    }

}