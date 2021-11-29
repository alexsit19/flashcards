package com.example.flashcards.repository.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flashcards.FlashcardsApp
import com.example.flashcards.repository.DB_NAME
import com.example.flashcards.repository.DB_VERSION
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Card::class, Stack::class], version = DB_VERSION, exportSchema = false)
public abstract class FlashcardsDatabase : RoomDatabase()  {

    abstract fun flashcardsDao(): FlashcardsDao

    companion object {

        @Volatile
        private var INSTANCE: FlashcardsDatabase? = null

        fun getDatabase(
            context: FlashcardsApp?,
            scope: CoroutineScope
        ): FlashcardsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context!!.applicationContext,
                    FlashcardsDatabase::class.java,
                    DB_NAME
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}