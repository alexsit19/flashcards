package com.example.flashcards.domain

import com.example.flashcards.data.Repository
import com.example.flashcards.data.room.Stack
import javax.inject.Inject

class InsertStackUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(stack: Stack): Long = repository.insertStack(stack)
}
