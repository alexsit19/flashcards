package com.example.flashcards.domain

import com.example.flashcards.data.Repository
import javax.inject.Inject

class GetAllCardsInStackUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(stackId: Long) = repository.getAllCardsInStack(stackId)
}