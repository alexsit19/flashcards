package com.example.flashcards.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.flashcards.R
import com.example.flashcards.data.room.Stack
import com.example.flashcards.databinding.AddStackFragmentBinding
import com.example.flashcards.presentation.viewmodels.AddStackFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddStackFragment : Fragment(R.layout.add_stack_fragment) {

    private lateinit var binding: AddStackFragmentBinding
    private val viewModel: AddStackFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddStackFragmentBinding.bind(view)
        binding.toolbarAddStackFragment.inflateMenu(R.menu.menu_ok_cancel)
        binding.toolbarAddStackFragment.setNavigationIcon(R.drawable.ic_baseline_close_24)
        binding.toolbarAddStackFragment.setNavigationOnClickListener{
            Log.d("DEBUG", "Нажата кнопка назад")
        }
        binding.toolbarAddStackFragment.setOnMenuItemClickListener {
            Log.d("DEBUG", "нажата кнопка ок")
            viewModel.insert(Stack(0, "name"))
            true
        }
    }
}