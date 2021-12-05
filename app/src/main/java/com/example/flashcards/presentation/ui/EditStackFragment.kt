package com.example.flashcards.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.flashcards.R
import com.example.flashcards.data.STACK_ID
import com.example.flashcards.data.STACK_NAME
import com.example.flashcards.data.room.Stack
import com.example.flashcards.databinding.EditStackFragmentBinding
import com.example.flashcards.databinding.MainFragmentBinding
import com.example.flashcards.presentation.viewmodels.EditStackFragmentViewModel

class EditStackFragment : Fragment(R.layout.edit_stack_fragment) {

    private var _binding: EditStackFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel: EditStackFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EditStackFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = EditStackFragmentBinding.bind(view)
        binding.toolbarEditStackFragment.inflateMenu(R.menu.menu_ok_cancel)
        binding.toolbarEditStackFragment.setNavigationIcon(R.drawable.ic_baseline_close_24)
        binding.toolbarEditStackFragment.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_editStackFragment_to_mainFragment)
        }

        val arguments = requireArguments()
        val stackId = arguments.getLong(STACK_ID)
        val stackName = arguments.getString(STACK_NAME)
        binding.textInputEdittext.setText(stackName)

        binding.textInputEdittext.doOnTextChanged { text, start, before, count ->
            if(text?.length == 0) {
                binding.textInputLayout.error = getString(R.string.error_input_layout)

            } else {
                binding.textInputLayout.error = null
            }
        }

        binding.toolbarEditStackFragment.setOnMenuItemClickListener {
            Log.d("DEBUG", "нажата кнопка ок")
            val stackNameEditText = binding.textInputEdittext.text.toString()
            if (stackNameEditText.isNotEmpty()) {
                binding.textInputLayout.error = null
                viewModel.updateStack(Stack(stackId, stackNameEditText))
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