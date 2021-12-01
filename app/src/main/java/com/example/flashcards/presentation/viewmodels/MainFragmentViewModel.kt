package com.example.flashcards.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.flashcards.data.room.Stack
import com.example.flashcards.domain.GetAllStacksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val getAllStacksUseCase: GetAllStacksUseCase
    ) : ViewModel() {

    fun getAllStacks() : Flow<List<Stack>> = getAllStacksUseCase()
}