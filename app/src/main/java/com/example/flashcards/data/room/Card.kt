package com.example.flashcards.data.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "cards",
    foreignKeys = [ForeignKey(
        entity = Stack::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("stack_id"),
        onDelete = CASCADE
    )]
)
data class Card(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "stack_id")
    val stackId: Long,
    @ColumnInfo(name = "front_side")
    val frontSide: String,
    @ColumnInfo(name = "back_side")
    val backSide: String
)