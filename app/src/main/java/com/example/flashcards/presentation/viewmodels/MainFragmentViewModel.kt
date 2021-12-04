package com.example.flashcards.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.data.room.Stack
import com.example.flashcards.domain.DeleteStackUseCase
import com.example.flashcards.domain.GetAllStacksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val getAllStacksUseCase: GetAllStacksUseCase,
    private val deleteStackUseCase: DeleteStackUseCase
    ) : ViewModel() {

    private val _stackListIsEmptyUiState = MutableStateFlow(true)
    val stackListIsEmptyUiState: StateFlow<Boolean> = _stackListIsEmptyUiState
    private val allStackFlow = getAllStacksUseCase()

    init {
        setUiState()
    }

    private fun setUiState() {
        viewModelScope.launch {
            allStackFlow.collect { list ->
                _stackListIsEmptyUiState.value = list.isEmpty()
                if(list.isEmpty()) {
                    Log.d("DEBUG", "LIST IS EMPTY")
                }
            }
        }
    }

    fun getAllStacks() : Flow<List<Stack>> {
        return allStackFlow
    }

    fun deleteStack(stack: Stack) {
           viewModelScope.launch {
               deleteStackUseCase(stack)
           }
    }
}