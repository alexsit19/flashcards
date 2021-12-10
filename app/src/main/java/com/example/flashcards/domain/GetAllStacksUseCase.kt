package com.example.flashcards.domain

import com.example.flashcards.data.Repository
import javax.inject.Inject

class GetAllStacksUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(sortBy: String) = repository.getAllStacks(sortBy)
}
