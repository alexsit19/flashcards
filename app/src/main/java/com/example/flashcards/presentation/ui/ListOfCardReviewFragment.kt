package com.example.flashcards.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.flashcards.R
import com.example.flashcards.data.CARD_BACK_SIDE
import com.example.flashcards.data.CARD_FRONT_SIDE
import com.example.flashcards.data.CARD_ID
import com.example.flashcards.data.STACK_ID
import com.example.flashcards.data.room.Card
import com.example.flashcards.databinding.ListOfCardReviewFragmentBinding
import com.example.flashcards.presentation.ui.recyclers.CardAdapter
import com.example.flashcards.presentation.ui.recyclers.CardReviewItemClickListener
import com.example.flashcards.presentation.viewmodels.ListOfCardReviewFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListOfCardReviewFragment : Fragment(),
    CardReviewItemClickListener {

    private var _binding: ListOfCardReviewFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel: ListOfCardReviewFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_listOfCardReviewFragment_to_mainFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListOfCardReviewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val sortByString = prefs.getString("card_sort_key", "id") as String

        val adapter = CardAdapter(this)
        val stackId = requireArguments().getLong(STACK_ID)
        binding.cardReviewRecycler.adapter = adapter

        initToolbar()

        binding.addNewCardButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_listOfCardReviewFragment_to_addCardFragment,
                bundleOf(STACK_ID to stackId)
            )
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getAllCardsInStack(stackId, sortByString)?.collect() { list ->
                    adapter.submitList(list)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.listIsEmptyUiState
                .onEach {
                    when (it) {
                        true -> binding.centralText.visibility = View.VISIBLE
                        false -> binding.centralText.visibility = View.GONE
                    }
                }.collect()
            }
        }
    }

    private fun initToolbar() {
        binding.toolbarListCardReview.inflateMenu(R.menu.menu_main_fragment)
        binding.toolbarListCardReview.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbarListCardReview.setTitleTextColor(resources.getColor(R.color.white))
        binding.toolbarListCardReview.setTitle(R.string.app_name)
        binding.toolbarListCardReview.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_listOfCardReviewFragment_to_mainFragment)
        }
        binding.toolbarListCardReview.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> {
                    findNavController().navigate(R.id.open_settings_fragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun deleteCard(card: Card) {
        viewModel.deleteCard(card)
    }

    override fun editCard(card: Card) {
        findNavController().navigate(
            R.id.action_listOfCardReviewFragment_to_editCardFragment,
            bundleOf(
                STACK_ID to card.stackId,
                CARD_ID to card.id,
                CARD_FRONT_SIDE to card.frontSide,
                CARD_BACK_SIDE to card.backSide
            )
        )
    }
}
