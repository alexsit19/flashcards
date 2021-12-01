package com.example.flashcards.domain

import com.example.flashcards.data.Repository
import com.example.flashcards.data.room.Card
import javax.inject.Inject

class DeleteCardUseCse @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(card: Card) = repository.deleteCard(card)
}