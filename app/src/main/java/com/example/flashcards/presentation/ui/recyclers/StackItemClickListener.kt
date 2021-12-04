package com.example.flashcards.presentation.ui.recyclers

import com.example.flashcards.data.room.Stack

interface StackItemClickListener {

    fun openCardList(stackId: Long)

    fun deleteStack(stack: Stack)

    fun editStack(stack: Stack)

    fun openCardListReview(stackId: Long)
}