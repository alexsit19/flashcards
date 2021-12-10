package com.example.flashcards.domain

import com.example.flashcards.data.Repository
import javax.inject.Inject

class GetCardsWhereStackIdMax @Inject constructor(private val repository: Repository) {
    operator fun invoke() = repository.getCardsWhereStackIdMax()
}
