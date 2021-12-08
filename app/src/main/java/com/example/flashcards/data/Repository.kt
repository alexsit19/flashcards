package com.example.flashcards.data

import com.example.flashcards.data.room.Card
import com.example.flashcards.data.room.Stack
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getAllStacks(sortBy: String) : Flow<List<Stack>>

    fun getAllCardsInStack(stackId: Long, sortBy: String = "id") : Flow<List<Card>>

    fun getCardsWhereStackIdMax() : Flow<List<Card>>

    suspend fun getStackWithMaxId() : Stack

    suspend fun deleteCard(card: Card)

    suspend fun deleteStack(stack: Stack)

    suspend fun insertStack(stack: Stack) : Long

    suspend fun insertCard(card: Card)

    suspend fun updateStack(stack: Stack)

    suspend fun updateCard(card: Card)
}