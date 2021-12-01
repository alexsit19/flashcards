package com.example.flashcards.di

import com.example.flashcards.data.FlashcardsRepository
import com.example.flashcards.data.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
//@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideFlashcardRepository(impl: FlashcardsRepository) : Repository
}
