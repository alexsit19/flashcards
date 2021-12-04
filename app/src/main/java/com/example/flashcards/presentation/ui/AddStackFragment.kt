package com.example.flashcards.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.flashcards.R
import com.example.flashcards.data.room.Stack
import com.example.flashcards.databinding.AddStackFragmentBinding
import com.example.flashcards.presentation.viewmodels.AddStackFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddStackFragment : Fragment(R.layout.add_stack_fragment) {

    private var _binding: AddStackFragmentBinding? = null
    private val binding: AddStackFragmentBinding = requireNotNull(_binding)
    private val viewModel: AddStackFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = AddStackFragmentBinding.bind(view)
        binding.toolbarAddStackFragment.inflateMenu(R.menu.menu_ok_cancel)
        binding.toolbarAddStackFragment.setNavigationIcon(R.drawable.ic_baseline_close_24)
        binding.toolbarAddStackFragment.setNavigationOnClickListener{
            findNavController().navigate(R.id.action_addStackFragment_to_mainFragment)
        }

        binding.textInputEdittext.doOnTextChanged { text, start, before, count ->
            if(text?.length == 0) {
                binding.textInputLayout.error = getString(R.string.error_input_layout)

            } else {
                binding.textInputLayout.error = null
            }
        }

        binding.toolbarAddStackFragment.setOnMenuItemClickListener {
            Log.d("DEBUG", "нажата кнопка ок")
            val stackName = binding.textInputEdittext.text.toString()
            if (stackName.isNotEmpty()) {
                binding.textInputLayout.error = null
                viewModel.insert(Stack(0, stackName))
                findNavController().navigate(
                    R.id.action_addStackFragment_to_listOfCardReviewFragment
                )
            } else {
                binding.textInputLayout.error = getString(R.string.error_input_layout)
            }
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}