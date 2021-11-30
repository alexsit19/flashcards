package com.example.flashcards.data

import com.example.flashcards.data.room.Card
import com.example.flashcards.data.room.FlashcardsDao
import com.example.flashcards.data.room.Stack

class FlashcardsRepository(private val flashcardsDao: FlashcardsDao) : Repository {

    override fun getAllStacks() = flashcardsDao.getAllStacks()

    override fun getAllCardsInStack(stackId: Long) = flashcardsDao.getAllCardsInStack(stackId)

    override suspend fun deleteCard(card: Card) = flashcardsDao.deleteCard(card)

    override suspend fun deleteStack(stack: Stack) = flashcardsDao.deleteStack(stack)

    override suspend fun insertStack(stack: Stack) = flashcardsDao.insertStack(stack)

    override suspend fun insertCard(card: Card) = flashcardsDao.insertCard(card)

    override suspend fun updateStack(stack: Stack) = flashcardsDao.updateStack(stack)

    override suspend fun updateCard(card: Card) = flashcardsDao.updateCard(card)
}