package com.example.flashcards.presentation.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.flashcards.R
import com.example.flashcards.databinding.AddCardFragmentBinding
import com.example.flashcards.databinding.AddStackFragmentBinding
import com.example.flashcards.presentation.viewmodels.AddCardFragmentViewModel
import com.example.flashcards.presentation.viewmodels.AddStackFragmentViewModel

class AddCardFragment : Fragment(R.layout.add_card_fragment) {

    private var _binding: AddCardFragmentBinding? = null
    private val binding: AddCardFragmentBinding get() = requireNotNull(_binding)
    private val viewModel: AddCardFragmentViewModel by viewModels()
}