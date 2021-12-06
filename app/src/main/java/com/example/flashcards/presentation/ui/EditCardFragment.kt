package com.example.flashcards.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.flashcards.R
import com.example.flashcards.data.CARD_BACK_SIDE
import com.example.flashcards.data.CARD_FRONT_SIDE
import com.example.flashcards.data.CARD_ID
import com.example.flashcards.data.STACK_ID
import com.example.flashcards.data.room.Card
import com.example.flashcards.databinding.AddCardFragmentBinding
import com.example.flashcards.databinding.EditCardFragmentBinding
import com.example.flashcards.presentation.viewmodels.EditCardFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditCardFragment : Fragment(R.layout.edit_card_fragment) {

    private var _binding: EditCardFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel: EditCardFragmentViewModel by viewModels()
    private var stackId = -1L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EditCardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = requireArguments()
        stackId = args.getLong(STACK_ID)
        val cardId = args.getLong(CARD_ID)

        binding.textInputEdittextBackSide.setText(args.getString(CARD_BACK_SIDE))
        binding.textInputEdittextFrontSide.setText(args.getString(CARD_FRONT_SIDE))

            initToolbar()
            setOnChangeListeners()

            binding.toolbarEditCardFragment.setOnMenuItemClickListener {
                val frontSideText = binding.textInputEdittextFrontSide.text.toString()
                val backSideText = binding.textInputEdittextBackSide.text.toString()
                if (backSideText.isNotEmpty()) {
                    binding.textInputLayoutBackSide.error = null
                    if (frontSideText.isNotEmpty()) {
                        binding.textInputLayoutFrontSide.error = null
                        viewModel.updateCard(Card(cardId, stackId, frontSideText, backSideText))
                        findNavController().navigate(
                            R.id.action_editCardFragment_to_listOfCardReviewFragment,
                            bundleOf(STACK_ID to stackId)
                        )
                    } else {
                        binding.textInputLayoutFrontSide.error = getString(R.string.error_input_layout)
                    }

                } else {
                    binding.textInputLayoutBackSide.error = getString(R.string.error_input_layout)
                }


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
            binding.toolbarEditCardFragment.inflateMenu(R.menu.menu_ok_cancel)
            binding.toolbarEditCardFragment.setNavigationIcon(R.drawable.ic_baseline_close_24)
            binding.toolbarEditCardFragment.setNavigationOnClickListener {
                findNavController().navigate(
                    R.id.action_editCardFragment_to_listOfCardReviewFragment,
                    bundleOf(STACK_ID to stackId)
                )
            }
        }
}