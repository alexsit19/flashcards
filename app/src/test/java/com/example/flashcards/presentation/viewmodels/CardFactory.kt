package com.example.flashcards.presentation.viewmodels

import com.example.flashcards.data.room.Card

fun createCard(
    id: Long,
    stackId: Long,
    frontSide: String,
    backSide: String
): Card {
    return Card(
        id = id,
        stackId = stackId,
        frontSide = frontSide,
        backSide = backSide,
        )
}
