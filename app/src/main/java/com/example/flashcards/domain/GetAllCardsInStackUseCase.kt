package com.example.flashcards.domain

import com.example.flashcards.data.FlashcardsRepository

class GetAllCardsInStackUseCase(private val repository: FlashcardsRepository) {
    operator fun invoke(stackId: Long) = repository.getAllCardsInStack(stackId)
}