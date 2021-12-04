package com.example.flashcards.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.data.room.Stack
import com.example.flashcards.domain.UpdateStackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditStackFragmentViewModel @Inject constructor(
    private val updateStackUseCase: UpdateStackUseCase
) : ViewModel() {

    fun updateStack(stack: Stack) {
        viewModelScope.launch {
            updateStackUseCase(stack)
        }
    }
}