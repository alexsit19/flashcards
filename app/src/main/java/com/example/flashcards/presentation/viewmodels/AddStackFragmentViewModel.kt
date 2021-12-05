package com.example.flashcards.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.data.room.Stack
import com.example.flashcards.domain.GetStackWithMaxId
import com.example.flashcards.domain.InsertStackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddStackFragmentViewModel @Inject constructor(
    private val insertStackUseCase: InsertStackUseCase,
    private val getStackWithMaxId: GetStackWithMaxId
) : ViewModel() {

    val s = MutableStateFlow<Long>(-1)

    fun getStack() {
        viewModelScope.launch {

        }
    }

    fun insert(stack: Stack) : Long {
        viewModelScope.launch(Dispatchers.IO) {
            insertStackUseCase(stack)
            s.value = getStackWithMaxId().id

        }
        return stack.id
    }
}
