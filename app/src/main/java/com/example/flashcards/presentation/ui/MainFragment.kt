package com.example.flashcards.presentation.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.flashcards.R
import com.example.flashcards.data.STACK_ID
import com.example.flashcards.data.STACK_NAME
import com.example.flashcards.data.room.Stack
import com.example.flashcards.databinding.MainFragmentBinding
import com.example.flashcards.presentation.ui.recyclers.StackItemClickListener
import com.example.flashcards.presentation.ui.recyclers.StackAdapter
import com.example.flashcards.presentation.viewmodels.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val prefTheme = pref.getString("themes_key", "light") as String

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            when (prefTheme) {
                "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMainMenuAndTitle()

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val sortByString = prefs.getString("stack_sort_key", "id") as String

        val adapter = StackAdapter(this)
        binding.stackRecycler.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getAllStacks(sortByString)?.collect() { list ->
                    adapter.submitList(list)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.stackListIsEmptyUiState
                    .onEach {
                        when (it) {
                            true -> binding.centralText.visibility = View.VISIBLE
                            false -> binding.centralText.visibility = View.GONE
                        }
                    }.collect()
            }
        }

        binding.toolbarMainFragment.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> {
                    findNavController().navigate(R.id.open_settings_fragment)
                    true
                }
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
