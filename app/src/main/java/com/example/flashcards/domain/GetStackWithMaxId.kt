package com.example.flashcards.domain

import com.example.flashcards.data.Repository
import javax.inject.Inject

class GetStackWithMaxId @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.getStackWithMaxId()
}
