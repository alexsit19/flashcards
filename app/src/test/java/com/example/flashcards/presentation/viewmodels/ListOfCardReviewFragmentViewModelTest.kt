package com.example.flashcards.presentation.viewmodels

import com.example.flashcards.data.room.Card
import com.example.flashcards.data.room.Stack
import com.example.flashcards.domain.DeleteCardUseCase
import com.example.flashcards.domain.GetAllCardsInStackUseCase
import com.example.flashcards.utils.viewModelTestingRules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ListOfCardReviewFragmentViewModelTest {
    @get:Rule
    val viewModelRule = viewModelTestingRules()
    private val repository = StubRepository()
    private val getAllCardsInStackUseCase = GetAllCardsInStackUseCase(repository)
    private val deleteCardUseCase = DeleteCardUseCase(repository)
    private val viewModel = ListOfCardReviewFragmentViewModel(
        getAllCardsInStackUseCase, deleteCardUseCase)

    @ExperimentalCoroutinesApi
    @Test
    fun `cardsOutput returns card list`() {
        val stacks = listOf(
            createStack(id = 1, name = "stack1"),
            createStack(id = 2, name = "stack2"),
            createStack(id = 3, name = "stack3")
        )

        val cards = listOf(
            createCard(id = 1, stackId = 2, frontSide = "one", backSide = "two"),
            createCard(id = 2, stackId = 2, frontSide = "three", backSide = "four")
        )

        repository.setCards(cards)
        repository.setSacks(stacks)

        val state = false

        runBlockingTest {
            viewModel.getAllCardsInStack(1,"id")?.collect { list ->

                Assert.assertEquals(state, viewModel.listIsEmptyUiState.value)
                Assert.assertEquals(cards, list)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `cards Output return empty card list`() {
        val cards = emptyList<Card>()
        repository.setCards(cards)
        val state = true

        runBlockingTest {
            viewModel.getAllCardsInStack(0L, "id")?.collect {

                Assert.assertEquals(state, viewModel.listIsEmptyUiState.value)
                Assert.assertEquals(cards, emptyList<Stack>())
            }
        }
    }
}

