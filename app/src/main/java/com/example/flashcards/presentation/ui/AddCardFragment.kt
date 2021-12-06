package com.example.flashcards.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.flashcards.R
import com.example.flashcards.data.STACK_ID
import com.example.flashcards.data.room.Card
import com.example.flashcards.databinding.AddCardFragmentBinding
import com.example.flashcards.databinding.AddStackFragmentBinding
import com.example.flashcards.presentation.viewmodels.AddCardFragmentViewModel
import com.example.flashcards.presentation.viewmodels.AddStackFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCardFragment : Fragment(R.layout.add_card_fragment) {

    private var _binding: AddCardFragmentBinding? = null
    private val binding: AddCardFragmentBinding get() = requireNotNull(_binding)
    private val viewModel: AddCardFragmentViewModel by viewModels()
    private var stackId = -1L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = AddCardFragmentBinding.bind(view)
        stackId = requireArguments().getLong(STACK_ID)

        initToolbar()
        setOnChangeListeners()

        binding.toolbarAddCardFragment.setOnMenuItemClickListener {
            val frontSideText = binding.textInputEdittextFrontSide.text.toString()
            val backSideText = binding.textInputEdittextBackSide.text.toString()
            if (backSideText.isNotEmpty()) {
                binding.textInputLayoutBackSide.error = null
                if (frontSideText.isNotEmpty()) {
                    binding.textInputLayoutFrontSide.error = null
                    viewModel.insertCard(Card(0, stackId, frontSideText, backSideText))
                    findNavController().navigate(
                    R.id.action_addCardFragment_to_listOfCardReviewFragment,
                    bundleOf(STACK_ID to stackId)
                )
                } else {
                    binding.textInputLayoutFrontSide.error = getString(R.string.error_input_layout)
                }

            } else {
                binding.textInputLayoutBackSide.error = getString(R.string.error_input_layout)
            }

//            if (backSideText.isNotEmpty() {
//                binding.textInputLayoutBackSide.error = null
//                    if (frontSideText.isNotEmpty()) {
//                        binding.textInputEdittextFrontSide.error = null
//                    } else {
//                        binding.textInputLayoutFrontSide.error = getString(R.string.error_input_layout)
//                    }
//
//                viewModel.insertCard(Card(0L, stackId, frontSideText, backSideText))
//                findNavController().navigate(
//                    R.id.action_addCardFragment_to_listOfCardReviewFragment,
//                    bundleOf(STACK_ID to stackId)
//                )
//            } else {
//
//            }
            true
        }
    }

    private fun setOnChangeListeners() {
        binding.textInputEdittextFrontSide.doOnTextChanged { text, start, before, count ->
            if(text?.length == 0) {
                binding.textInputEdittextFrontSide.error = getString(R.string.error_input_layout)

            } else {
                binding.textInputLayoutFrontSide.error = null
            }
        }

        binding.textInputEdittextBackSide.doOnTextChanged { text, start, before, count ->
            if(text?.length == 0) {
                binding.textInputEdittextBackSide.error = getString(R.string.error_input_layout)

            } else {
                binding.textInputEdittextBackSide.error = null
            }
        }
    }

    private fun initToolbar() {
        binding.toolbarAddCardFragment.inflateMenu(R.menu.menu_ok_cancel)
        binding.toolbarAddCardFragment.setNavigationIcon(R.drawable.ic_baseline_close_24)
        binding.toolbarAddCardFragment.setNavigationOnClickListener {
            findNavController().navigate(
                R.id.action_addCardFragment_to_listOfCardReviewFragment,
                bundleOf(STACK_ID to stackId)
            )
        }
    }
}
