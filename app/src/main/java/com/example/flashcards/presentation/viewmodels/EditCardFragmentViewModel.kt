package com.example.flashcards.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.data.room.Card
import com.example.flashcards.domain.UpdateCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCardFragmentViewModel @Inject constructor(
    private val updateCardUseCase: UpdateCardUseCase
) : ViewModel() {

    fun updateCard(card: Card) {
        viewModelScope.launch {
            updateCardUseCase(card)
        }
    }
}
