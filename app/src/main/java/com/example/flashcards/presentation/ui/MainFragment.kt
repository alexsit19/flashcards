package com.example.flashcards.presentation.ui

import android.content.Context
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
import com.example.flashcards.presentation.ui.recyclers.RecyclerViewItemClickListener
import com.example.flashcards.presentation.ui.recyclers.StackAdapter
import com.example.flashcards.presentation.viewmodels.MainFragmentViewModel
//import com.example.flashcards.presentation.viewmodels.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment),
    RecyclerViewItemClickListener {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)
        setMainMenuAndTitle()

        val adapter = StackAdapter(this)
        binding.stackRecycler.adapter = adapter

        lifecycleScope.launch {
            viewModel.stackListIsEmptyUiState.collect {
                if(it) {
                    binding.centralText.visibility = View.VISIBLE
                    Log.d("DEBUG", "СПИСОК ПУСТОЙ")
                } else {

                    binding.centralText.visibility = View.GONE
                    viewModel.getAllStacks().collect() { list ->
                        adapter.submitList(list)
                    }
                }
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

    override fun onItemClickListener() {
        Log.d("DEBUG", "fragment on clickListener")
    }

    override fun onLongItemClickListener() {
        binding.toolbarMainFragment.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        setEditMenu()
        binding.toolbarMainFragment.setNavigationOnClickListener {
            setMainMenuAndTitle()
            findNavController().navigate(R.id.action_mainFragment_self)
        }
    }

    private fun setMainMenuAndTitle() {
        binding.toolbarMainFragment.menu.clear()
        binding.toolbarMainFragment.setTitle(R.string.app_name)
        binding.toolbarMainFragment.inflateMenu(R.menu.menu_main_fragment)
    }

    private fun setEditMenu() {
        binding.toolbarMainFragment.menu.clear()
        binding.toolbarMainFragment.title = ""
        binding.toolbarMainFragment.inflateMenu(R.menu.menu_edit_stack_item)
    }
}