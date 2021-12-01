package com.example.flashcards.domain

import com.example.flashcards.data.Repository
import com.example.flashcards.data.room.Stack
import javax.inject.Inject

class UpdateStackUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(stack: Stack) = repository.updateStack(stack)
}