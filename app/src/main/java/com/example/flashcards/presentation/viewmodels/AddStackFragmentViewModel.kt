package com.example.flashcards.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.data.room.Stack
import com.example.flashcards.domain.InsertStackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddStackFragmentViewModel @Inject constructor(
    private val insertStackUseCase: InsertStackUseCase
) : ViewModel() {
    fun insert(stack: Stack) {
        Log.d("DEBUG", "insert")
        viewModelScope.launch(Dispatchers.IO) {
            insertStackUseCase(stack)

        }
    }

}