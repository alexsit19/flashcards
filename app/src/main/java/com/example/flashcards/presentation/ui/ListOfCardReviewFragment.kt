package com.example.flashcards.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flashcards.R
import com.example.flashcards.data.CARD_BACK_SIDE
import com.example.flashcards.data.CARD_FRONT_SIDE
import com.example.flashcards.data.STACK_ID
import com.example.flashcards.data.room.Card
import com.example.flashcards.databinding.ListOfCardReviewFragmentBinding
import com.example.flashcards.databinding.MainFragmentBinding
import com.example.flashcards.presentation.ui.recyclers.CardAdapter
import com.example.flashcards.presentation.ui.recyclers.CardItemClickListener
import com.example.flashcards.presentation.viewmodels.ListOfCardReviewFragmentViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListOfCardReviewFragment : Fragment(R.layout.list_of_card_review_fragment),
    CardItemClickListener {

    private var _binding: ListOfCardReviewFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel: ListOfCardReviewFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ListOfCardReviewFragmentBinding.bind(view)
        val adapter = CardAdapter(this)
        binding.cardReviewRecycler.adapter = adapter

        binding.addNewCardButton.setOnClickListener{
            findNavController().navigate(
                R.id.action_listOfCardReviewFragment_to_addCardFragment
            )
        }

        val stackId = requireArguments().getLong(STACK_ID)
        Log.d("DEBUG", "STACK_ID = $stackId")

        viewModel.getAllCardsInStack(stackId)?.onEach { list ->
            adapter.submitList(list)
        }

        lifecycleScope.launchWhenCreated {
            viewModel.listIsEmptyUiState
                .onEach {
                    when (it) {
                        true -> binding.centralText.visibility = View.VISIBLE
                        false -> binding.centralText.visibility = View.GONE
                    }
                }.collect()
        }

    }

    fun initToolbar() {
        binding.toolbarListCardReview.inflateMenu(R.menu.menu_main_fragment)
        binding.toolbarListCardReview.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbarListCardReview.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_listOfCardReviewFragment_to_mainFragment)
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
                STACK_ID to card.id,
                CARD_FRONT_SIDE to card.frontSide,
                CARD_BACK_SIDE to card.backSide
            )
        )
    }
}