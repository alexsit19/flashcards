package com.example.flashcards.domain

import com.example.flashcards.data.FlashcardsRepository
import com.example.flashcards.data.room.Card

class DeleteCardUseCse(private val repository: FlashcardsRepository) {
    suspend operator fun invoke(card: Card) = repository.deleteCard(card)
}