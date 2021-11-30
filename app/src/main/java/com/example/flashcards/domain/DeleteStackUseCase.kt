package com.example.flashcards.domain

import com.example.flashcards.data.FlashcardsRepository
import com.example.flashcards.data.room.Stack

class DeleteStackUseCase(private val repository: FlashcardsRepository) {
    suspend operator fun invoke(stack: Stack) = repository.deleteStack(stack)
}