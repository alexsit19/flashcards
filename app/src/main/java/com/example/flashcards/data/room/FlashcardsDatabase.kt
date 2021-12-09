package com.example.flashcards.data.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flashcards.FlashcardsApp
import com.example.flashcards.data.DB_VERSION
import com.example.flashcards.data.DB_NAME

@Database(entities = [Card::class, Stack::class], version = DB_VERSION, exportSchema = false)
public abstract class FlashcardsDatabase : RoomDatabase()  {

    abstract fun flashcardsDao(): FlashcardsDao

    companion object {

        @Volatile
        private var INSTANCE: FlashcardsDatabase? = null

    }
}