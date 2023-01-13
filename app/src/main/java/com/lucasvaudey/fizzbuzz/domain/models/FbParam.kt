package com.lucasvaudey.fizzbuzz.domain.models

import com.lucasvaudey.fizzbuzz.data.entities.FbEntry

/**
 * Fake Fizz Buzz model to show the difference beetween models and entities
 */
data class FbParam(
    val number1: Int,
    val number2: Int,
    val text1: String,
    val text2: String
){
    constructor(fbEntry: FbEntry): this(fbEntry.firstNumber, fbEntry.secondNumber, fbEntry.firstWord, fbEntry.secondWord)
}
