package com.example.flashcards.presentation.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.ListPreference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen
import com.example.flashcards.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            val preferenceCategory = findPreference<PreferenceCategory>("theme_key_category")
            preferenceScreen.removePreference(preferenceCategory)
        }
    }
}