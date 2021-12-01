package com.example.flashcards.data

import android.util.Log
import com.example.flashcards.data.room.Card
import com.example.flashcards.data.room.FlashcardsDao
import com.example.flashcards.data.room.Stack
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlashcardsRepository @Inject constructor(
    private val flashcardsDao: FlashcardsDao
    ) : Repository {

    override fun getAllStacks(): Flow<List<Stack>> {
        Log.d("DEBUG", "getAllStacks Repository")
        return flashcardsDao.getAllStacks()
    }

    override fun getAllCardsInStack(stackId: Long) = flashcardsDao.getAllCardsInStack(stackId)

    override suspend fun deleteCard(card: Card) = flashcardsDao.deleteCard(card)

    override suspend fun deleteStack(stack: Stack) = flashcardsDao.deleteStack(stack)

    override suspend fun insertStack(stack: Stack) {
        Log.d("DEBUG", "insertStack from Repository")
        flashcardsDao.insertStack(stack)
    }

    override suspend fun insertCard(card: Card) = flashcardsDao.insertCard(card)

    override suspend fun updateStack(stack: Stack) = flashcardsDao.updateStack(stack)

    override suspend fun updateCard(card: Card) = flashcardsDao.updateCard(card)
}