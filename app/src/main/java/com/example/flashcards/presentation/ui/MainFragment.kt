package com.example.flashcards.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flashcards.R
import com.example.flashcards.databinding.MainFragmentBinding

class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var binding: MainFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        binding.toolbarMainFragment.setTitle(R.string.app_name)
        binding.toolbarMainFragment.inflateMenu(R.menu.menu_main_fragment)


        binding.toolbarMainFragment.setOnMenuItemClickListener {
            Log.d("DEBUG", "yF;FNJ VTY.")
            when(it.itemId) {
                R.id.action_settings -> true
                else -> false
            }
        }

        binding.addNewStackButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addStackFragment2)
        }
    }

}