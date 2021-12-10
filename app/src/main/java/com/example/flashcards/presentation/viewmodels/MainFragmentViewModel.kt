package com.example.flashcards.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.data.room.Stack
import com.example.flashcards.domain.DeleteStackUseCase
import com.example.flashcards.domain.GetAllStacksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val getAllStacksUseCase: GetAllStacksUseCase,
    private val deleteStackUseCase: DeleteStackUseCase
) : ViewModel() {

    private val _stackListIsEmptyUiState = MutableStateFlow(true)
    val stackListIsEmptyUiState: StateFlow<Boolean> = _stackListIsEmptyUiState
    var allStackFlow: Flow<List<Stack>>? = null

    private fun setUiState() {
        viewModelScope.launch {
            allStackFlow?.collect { list ->
                _stackListIsEmptyUiState.value = list.isEmpty()
            }
        }
    }

    fun getAllStacks(sortBy: String): Flow<List<Stack>>? {
        allStackFlow = getAllStacksUseCase(sortBy)
        setUiState()
        return allStackFlow
    }

    fun deleteStack(stack: Stack) {
        viewModelScope.launch {
            deleteStackUseCase(stack)
            }
    }
}
