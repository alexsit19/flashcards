package com.example.flashcards.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.data.room.Stack
import com.example.flashcards.domain.GetAllStacksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val getAllStacksUseCase: GetAllStacksUseCase
    ) : ViewModel() {

    private val _stackListIsEmptyUiState = MutableStateFlow(true)
    val stackListIsEmptyUiState: StateFlow<Boolean> = _stackListIsEmptyUiState
    private val _isEditUiState = MutableStateFlow(false)
    val isEditUiState: StateFlow<Boolean> = _isEditUiState
    private val allStackFlow = getAllStacksUseCase()

    init {
        setUiState()
    }

    private fun setUiState() {
        viewModelScope.launch {
            allStackFlow.collect { list ->
                _stackListIsEmptyUiState.value = list.isEmpty()
            }
        }
    }

    fun getAllStacks() : Flow<List<Stack>> {
        return allStackFlow
    }

    fun isEditUiState() {
        _isEditUiState.value = true
    }
}