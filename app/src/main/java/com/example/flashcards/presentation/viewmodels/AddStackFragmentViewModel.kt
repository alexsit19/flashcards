package com.example.flashcards.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.data.room.Stack
import com.example.flashcards.domain.GetStackWithMaxId
import com.example.flashcards.domain.InsertStackUseCase
import com.example.flashcards.domain.UpdateStackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddStackFragmentViewModel @Inject constructor(
    private val insertStackUseCase: InsertStackUseCase,
    private val getStackWithMaxId: GetStackWithMaxId,
    private val updateStackUseCase: UpdateStackUseCase
) : ViewModel() {

    val stateStackId = MutableStateFlow<Long>(-1)

    fun insert(stack: Stack) {
        viewModelScope.launch(Dispatchers.IO) {
            insertStackUseCase(stack)
            stateStackId.value = getStackWithMaxId().id

        }
    }

    fun updateStack(stack: Stack) {
        viewModelScope.launch {
            updateStackUseCase(stack)
        }
    }
}
