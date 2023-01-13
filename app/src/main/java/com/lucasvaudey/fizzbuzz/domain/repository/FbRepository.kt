package com.lucasvaudey.fizzbuzz.domain.repository

import com.lucasvaudey.fizzbuzz.domain.models.FbParam

abstract class FbRepository {
   abstract suspend fun saveEntry(number1: Int, number2: Int, text1: String, text2: String)
    abstract suspend fun getLastEntry():FbParam
}