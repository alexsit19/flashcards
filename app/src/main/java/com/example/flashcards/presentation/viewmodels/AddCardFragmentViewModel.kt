package com.example.flashcards.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.data.room.Card
import com.example.flashcards.domain.InsertCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCardFragmentViewModel @Inject constructor (
    private val insertCardUseCase: InsertCardUseCase
) : ViewModel() {

        fun insertCard(card: Card) {
            viewModelScope.launch {
                insertCardUseCase(card)
            }
        }
    }
