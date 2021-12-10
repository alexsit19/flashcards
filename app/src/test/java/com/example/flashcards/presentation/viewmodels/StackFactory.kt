package com.example.flashcards.presentation.viewmodels

import com.example.flashcards.data.room.Stack

fun createStack(
    id: Long =0L, name: String = ""
): Stack {
    return Stack(id = id, name = name)
}