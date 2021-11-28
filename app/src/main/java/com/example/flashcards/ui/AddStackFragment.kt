package com.example.flashcards.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.example.flashcards.R
import com.example.flashcards.databinding.AddStackFragmentBinding

class AddStackFragment : Fragment(R.layout.add_stack_fragment) {

    private lateinit var binding: AddStackFragmentBinding

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
            true
        }
    }
}