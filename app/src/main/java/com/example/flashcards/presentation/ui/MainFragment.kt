package com.example.flashcards.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flashcards.R
import com.example.flashcards.databinding.MainFragmentBinding
import com.example.flashcards.presentation.ui.recyclers.StackAdapter
import com.example.flashcards.presentation.viewmodels.MainFragmentViewModel
//import com.example.flashcards.presentation.viewmodels.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        binding.toolbarMainFragment.setTitle(R.string.app_name)
        binding.toolbarMainFragment.inflateMenu(R.menu.menu_main_fragment)

        val adapter = StackAdapter()
        binding.stackRecycler.adapter = adapter
        lifecycle.coroutineScope.launch { viewModel.getAllStacks().collect() {
            adapter.submitList(it)
            Log.d("DEBUG", "$it")
        }

         }


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