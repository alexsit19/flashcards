package com.example.flashcards.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardsDao {

    @Query("SELECT * FROM stack_names ORDER BY id DESC LIMIT 1")
    fun getStackWithMaxId() : Stack

    @Query("SELECT * FROM stack_names ORDER BY " +
            "(CASE " +
            "WHEN :sortBy='id' THEN id " +
            "WHEN :sortBy='name' THEN name " +
            "ELSE id " +
            "END)")
    fun getAllStacks(sortBy: String) : Flow<List<Stack>>

    @Query("SELECT * FROM cards WHERE stack_id = :stackId ORDER BY " +
            "(CASE " +
            "WHEN :sortBy='id' THEN id " +
            "WHEN :sortBy='front_side' THEN front_side " +
            "ELSE id " +
            "END)")
    fun getAllCardsInStack(stackId: Long, sortBy: String) : Flow<List<Card>>

    @Query("SELECT * FROM cards WHERE stack_id = (SELECT MAX(id) FROM stack_names)")
    fun getCardsWhereStackIdMax() : Flow<List<Card>>

    @Delete
    suspend fun deleteCard(card: Card)

    @Delete
    suspend fun deleteStack(stack: Stack)

    @Update
    suspend fun updateStack(stack: Stack)

    @Update
    suspend fun updateCard(card: Card)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCard(card: Card)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStack(stack: Stack) : Long

}