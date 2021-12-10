package com.example.flashcards.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flashcards.R
import com.example.flashcards.databinding.SettingsFragmentContainerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragmentContainer : Fragment(R.layout.settings_fragment_container) {

    private var _binding: SettingsFragmentContainerBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SettingsFragmentContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarSettingsFragment.setTitle(R.string.toolbar_settings_title)
        binding.toolbarSettingsFragment.setTitleTextColor(resources.getColor(R.color.white))
        binding.toolbarSettingsFragment.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbarSettingsFragment.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_settingsFragmentContainer_to_mainFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
