package com.example.flashcards.presentation.ui.recyclers

import com.example.flashcards.data.room.Card

interface CardReviewItemClickListener {

    fun deleteCard(card: Card)

    fun editCard(card: Card)
}
