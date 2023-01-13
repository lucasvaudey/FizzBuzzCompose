package com.lucasvaudey.fizzbuzz.data.repository

import com.lucasvaudey.fizzbuzz.data.dao.FbEntryDao
import com.lucasvaudey.fizzbuzz.data.entities.FbEntry
import com.lucasvaudey.fizzbuzz.domain.models.FbParam
import com.lucasvaudey.fizzbuzz.domain.repository.FbRepository
import javax.inject.Inject

class FbRepositoryImpl @Inject constructor(private val fbDao: FbEntryDao) : FbRepository() {
    override suspend fun saveEntry(number1: Int, number2: Int, text1: String, text2: String) {
        fbDao.deleteAll()
        fbDao.insert(FbEntry(number1, number2, text1, text2))
    }

    override suspend fun getLastEntry(): FbParam {
        return FbParam(fbDao.getTheLastEntry())
    }

}