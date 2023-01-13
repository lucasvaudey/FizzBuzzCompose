package com.lucasvaudey.fizzbuzz.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lucasvaudey.fizzbuzz.data.entities.FbEntry

@Dao
interface FbEntryDao {
    @Query("SELECT * FROM fbentry ORDER BY id DESC LIMIT 1")
    fun getTheLastEntry(): FbEntry

    @Insert
    fun insert(fbEntry: FbEntry)

    @Query("DELETE FROM fbentry")
    fun deleteAll()

}
