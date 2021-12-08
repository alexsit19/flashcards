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

    override fun getAllStacks(sortBy: String): Flow<List<Stack>> {
        return flashcardsDao.getAllStacks(sortBy)
    }

    override suspend fun getStackWithMaxId(): Stack {
        return flashcardsDao.getStackWithMaxId()
    }

    override fun getCardsWhereStackIdMax() : Flow<List<Card>> =
        flashcardsDao.getCardsWhereStackIdMax()

    override fun getAllCardsInStack(stackId: Long, sortBy: String) =
        flashcardsDao.getAllCardsInStack(stackId, sortBy)

    override suspend fun deleteCard(card: Card) = flashcardsDao.deleteCard(card)

    override suspend fun deleteStack(stack: Stack) = flashcardsDao.deleteStack(stack)

    override suspend fun insertStack(stack: Stack) : Long {
        flashcardsDao.insertStack(stack)
        return stack.id
    }

    override suspend fun insertCard(card: Card) = flashcardsDao.insertCard(card)

    override suspend fun updateStack(stack: Stack) = flashcardsDao.updateStack(stack)

    override suspend fun updateCard(card: Card) = flashcardsDao.updateCard(card)
}