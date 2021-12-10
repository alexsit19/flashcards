package com.example.flashcards.di

import android.content.Context
import androidx.room.Room
import com.example.flashcards.data.DB_NAME
import com.example.flashcards.data.room.FlashcardsDao
import com.example.flashcards.data.room.FlashcardsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideFlashcardsDao(dataBase: FlashcardsDatabase): FlashcardsDao {
        return dataBase.flashcardsDao()
    }

    @Provides
    @Singleton
    fun provideFlashcardsDatabase(@ApplicationContext context: Context): FlashcardsDatabase {
        return Room.databaseBuilder(
            context,
            FlashcardsDatabase::class.java,
            DB_NAME
        ).build()
    }
}
