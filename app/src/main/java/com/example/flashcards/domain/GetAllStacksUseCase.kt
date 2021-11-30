package com.example.flashcards.domain

import com.example.flashcards.data.FlashcardsRepository

class GetAllStacksUseCase(private val repository: FlashcardsRepository) {
    operator fun invoke() = repository.getAllStacks()
}