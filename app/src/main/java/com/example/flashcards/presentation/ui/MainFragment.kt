package com.example.flashcards.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flashcards.R
import com.example.flashcards.data.STACK_ID
import com.example.flashcards.data.STACK_NAME
import com.example.flashcards.data.room.Stack
import com.example.flashcards.databinding.MainFragmentBinding
import com.example.flashcards.presentation.ui.recyclers.StackItemClickListener
import com.example.flashcards.presentation.ui.recyclers.StackAdapter
import com.example.flashcards.presentation.viewmodels.MainFragmentViewModel
//import com.example.flashcards.presentation.viewmodels.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment),
    StackItemClickListener {

    private var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding get() = requireNotNull(_binding)
    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MainFragmentBinding.bind(view)
        setMainMenuAndTitle()

        val adapter = StackAdapter(this, requireContext())
        binding.stackRecycler.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.stackListIsEmptyUiState
                .onEach {
                    when (it) {
                        true -> binding.centralText.visibility = View.VISIBLE
                        false -> binding.centralText.visibility = View.GONE
                    }
                }.collect()
        }


        lifecycleScope.launch {
            viewModel.getAllStacks().collect() { list ->
                adapter.submitList(list)
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

    private fun setMainMenuAndTitle() {
        binding.toolbarMainFragment.navigationIcon = null
        binding.toolbarMainFragment.menu.clear()
        binding.toolbarMainFragment.setTitleTextColor(resources.getColor(R.color.white))
        binding.toolbarMainFragment.setTitle(R.string.app_name)
        binding.toolbarMainFragment.inflateMenu(R.menu.menu_main_fragment)
    }

//    private fun setEditMenu(stack: Stack) {
//        binding.toolbarMainFragment.menu.clear()
//        binding.toolbarMainFragment.title = ""
//        binding.toolbarMainFragment.inflateMenu(R.menu.menu_edit_stack_item)
//        binding.toolbarMainFragment.setOnMenuItemClickListener {
//            when(it.itemId) {
//                R.id.action_delete -> {
//                    deleteStack(stack)
//                    setMainMenuAndTitle()
//                    true
//                }
//                else -> false
//            }
//        }
//    }

    override fun openCardList(stackId: Long) {
        findNavController().navigate(
            R.id.action_mainFragment_to_listOfCardsFragment,
            bundleOf(STACK_ID to stackId)
        )
    }

    override fun deleteStack(stack: Stack) {
        viewModel.deleteStack(stack)
    }

    override fun editStack(stack: Stack) {
        findNavController().navigate(
            R.id.action_mainFragment_to_editStackFragment,
            bundleOf(
                STACK_ID to stack.id,
                STACK_NAME to stack.name
            )
        )
    }

    override fun openCardListReview(stackId: Long) {
        findNavController().navigate(
            R.id.action_mainFragment_to_listOfCardReviewFragment2,
            bundleOf(
                STACK_ID to stackId
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}