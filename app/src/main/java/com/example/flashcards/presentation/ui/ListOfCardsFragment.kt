package com.example.flashcards.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.flashcards.R
import com.example.flashcards.data.STACK_ID
import com.example.flashcards.databinding.ListOfCardsFragmentBinding
import com.example.flashcards.presentation.ui.recyclers.CardPageAdapter
import com.example.flashcards.presentation.viewmodels.ListOfCardFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListOfCardsFragment : Fragment(R.layout.list_of_cards_fragment) {

    private var _binding: ListOfCardsFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel: ListOfCardFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_listOfCardsFragment_to_mainFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListOfCardsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()

        val stackId = requireArguments().getLong(STACK_ID)



        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getAllCardsInStack(stackId)?.collect() { list ->
                    Log.d("DEBUG", "$list")
                    val adapter = CardPageAdapter(list)
                    binding.viewPager.adapter = adapter
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.listIsEmptyUiState
                    .onEach {
                        when (it) {
                            true -> {
                                binding.centralText.visibility = View.VISIBLE
                                binding.addCardButton.visibility = View.VISIBLE
                                binding.addCardButton.setOnClickListener {
                                    findNavController().navigate(
                                        R.id.action_listOfCardsFragment_to_addCardFragment,
                                        bundleOf(
                                            STACK_ID to stackId
                                        )
                                    )
                                }
                            }
                            false -> {
                                binding.centralText.visibility = View.GONE
                                binding.addCardButton.visibility = View.GONE
                            }
                        }
                    }.collect()
            }
        }
    }

    private fun initToolbar() {
        binding.toolbarListOfCard.inflateMenu(R.menu.menu_main_fragment)
        binding.toolbarListOfCard.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbarListOfCard.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_listOfCardsFragment_to_mainFragment)
        }
    }
}