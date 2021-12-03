package com.example.flashcards.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.flashcards.domain.GetAllCardsInStackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ListOfCardReviewFragmentViewModel @Inject constructor(
    getAllCardsInStackUseCase: GetAllCardsInStackUseCase
    ) : ViewModel() {

    private val _listIsEmpty = MutableStateFlow(true)
    val listIsEmpty: StateFlow<Boolean> = _listIsEmpty

    fun getAllCardsInStack(stackId: Long) {

    }
}