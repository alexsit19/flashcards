package com.example.flashcards.presentation.ui.recyclers

import com.example.flashcards.data.room.Card

interface CardItemClickListener {

    fun deleteCard(card: Card)

    fun editCard(card: Card)
}