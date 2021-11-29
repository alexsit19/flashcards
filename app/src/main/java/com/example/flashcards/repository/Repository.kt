package com.example.flashcards.repository

import com.example.flashcards.repository.room.Card
import com.example.flashcards.repository.room.FlashcardsDao
import com.example.flashcards.repository.room.Stack
import kotlinx.coroutines.flow.Flow

class FlashcardsRepository(private val flashcardsDao: FlashcardsDao) {

    fun getAllStacks() = flashcardsDao.getAllStacks()

    fun getAllCardsInStack(stackId: Long) = flashcardsDao.getAllCardsInStack(stackId)

    suspend fun deleteCard(card: Card) = flashcardsDao.deleteCard(card)

    suspend fun deleteStack(stack: Stack) = flashcardsDao.deleteStack(stack)

    suspend fun insertStack(stack: Stack) = flashcardsDao.insertStack(stack)

    suspend fun insertCard(card: Card) = flashcardsDao.insertCard(card)

    suspend fun updateStack(stack: Stack) = flashcardsDao.updateStack(stack)

    suspend fun updateCard(card: Card) = flashcardsDao.updateCard(card)
}