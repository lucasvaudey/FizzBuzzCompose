package com.lucasvaudey.fizzbuzz.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FbEntry(
    @ColumnInfo(name = "first_number") val firstNumber: Int,
    @ColumnInfo(name = "second_number") val secondNumber: Int,
    @ColumnInfo(name = "first_word") val firstWord: String,
    @ColumnInfo(name = "second_word") val secondWord: String,
    @PrimaryKey(autoGenerate = true) val id: Long
){
    constructor(firstNumber: Int, secondNumber: Int, firstWord: String, secondWord: String) : this(firstNumber, secondNumber, firstWord, secondWord, 0)
}
