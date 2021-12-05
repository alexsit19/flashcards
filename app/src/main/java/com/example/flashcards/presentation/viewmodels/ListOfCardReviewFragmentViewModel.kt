package com.example.flashcards.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.data.FROM_MAIN_FRAGMENT
import com.example.flashcards.data.room.Card
import com.example.flashcards.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfCardReviewFragmentViewModel @Inject constructor(
    private val getAllCardsInStackUseCase: GetAllCardsInStackUseCase,
    private val deleteCardUseCase: DeleteCardUseCase,
    ) : ViewModel() {

    private val _listIsEmptyUiState = MutableStateFlow(true)
    val listIsEmptyUiState: StateFlow<Boolean> = _listIsEmptyUiState
    var allCardsFlow: Flow<List<Card>>? = null

    fun getAllCardsInStack(stackId: Long): Flow<List<Card>>? {
        allCardsFlow = getAllCardsInStackUseCase(stackId)
        setUiState()
        return allCardsFlow
    }

    private fun setUiState() {
        viewModelScope.launch {
                allCardsFlow?.collect { list ->
                    _listIsEmptyUiState.value = list.isEmpty()
                    if(list.isEmpty()) {
                        Log.d("DEBUG", "LIST IS EMPTY")
                    }
                }
            }
    }


    fun deleteCard(card: Card) {
        viewModelScope.launch {
        deleteCardUseCase(card)
        }
    }
}