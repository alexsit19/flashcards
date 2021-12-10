package com.example.flashcards.presentation.viewmodels

import com.example.flashcards.data.room.Stack
import com.example.flashcards.domain.DeleteStackUseCase
import com.example.flashcards.domain.GetAllStacksUseCase
import com.example.flashcards.utils.viewModelTestingRules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MainFragmentViewModelTest {
    @get:Rule
    val viewModelRule = viewModelTestingRules()
    private val repository = StubRepository()
    private val getAllStacksUseCase = GetAllStacksUseCase(repository)
    private val deleteStackUseCase = DeleteStackUseCase(repository)
    private val viewModel = MainFragmentViewModel(getAllStacksUseCase, deleteStackUseCase)

    @ExperimentalCoroutinesApi
    @Test
    fun `cardsOutput returns card list`() {
        val stacks = listOf(
            createStack(id = 1, name = "stack1"),
            createStack(id = 2, name = "stack2")
        )

        repository.setSacks(stacks)

        val state = false

        runBlockingTest {
            viewModel.getAllStacks("id")?.collect { list ->

                Assert.assertEquals(state, viewModel.stackListIsEmptyUiState.value)
                Assert.assertEquals(stacks, list)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `cards Output return empty card list`() {
        val stacks = emptyList<Stack>()
        repository.setSacks(stacks)
        val state = true

        runBlockingTest {
            viewModel.getAllStacks("id")?.collect {

                Assert.assertEquals(state, viewModel.stackListIsEmptyUiState.value)
                Assert.assertEquals(stacks, emptyList<Stack>())
            }
        }
    }
}
