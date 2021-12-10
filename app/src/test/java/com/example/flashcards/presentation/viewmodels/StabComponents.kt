package com.example.flashcards.presentation.viewmodels

import com.example.flashcards.data.Repository
import com.example.flashcards.data.room.Card
import com.example.flashcards.data.room.Stack
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StubRepository(): Repository {

    private var allStacks = flow { emit(emptyList<Stack>()) }
    private var allCards = flow { emit(emptyList<Card>()) }

    fun setSacks(list: List<Stack> = emptyList()) {
        allStacks = flow { emit(list) }
    }

    fun setCards(list: List<Card>) {
        allCards = flow { emit(list) }
    }

    override fun getAllStacks(sortBy: String): Flow<List<Stack>> {
        return allStacks
    }

    override fun getAllCardsInStack(stackId: Long, sortBy: String): Flow<List<Card>> {
        return allCards
    }

    override fun getCardsWhereStackIdMax(): Flow<List<Card>> {
        return allCards
    }

    override suspend fun getStackWithMaxId(): Stack {
        return Stack(0, "name")
    }

    override suspend fun deleteCard(card: Card) {

    }

    override suspend fun deleteStack(stack: Stack) {

    }

    override suspend fun insertStack(stack: Stack): Long {
        return 0L
    }

    override suspend fun insertCard(card: Card) {

    }

    override suspend fun updateStack(stack: Stack) {

    }

    override suspend fun updateCard(card: Card) {

    }
}


