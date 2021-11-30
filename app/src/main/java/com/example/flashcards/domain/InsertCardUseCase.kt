package com.example.flashcards.domain

import com.example.flashcards.data.FlashcardsRepository
import com.example.flashcards.data.room.Card

class InsertCardUseCase(private val repository: FlashcardsRepository) {
    suspend operator fun invoke(card: Card) = repository.insertCard(card)
}