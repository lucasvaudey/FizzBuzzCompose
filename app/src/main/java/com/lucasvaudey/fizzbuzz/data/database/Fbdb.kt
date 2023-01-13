package com.lucasvaudey.fizzbuzz.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lucasvaudey.fizzbuzz.data.dao.FbEntryDao
import com.lucasvaudey.fizzbuzz.data.entities.FbEntry

@Database(entities = [FbEntry::class], version = 1, exportSchema = false)
abstract class Fbdb : RoomDatabase() {
    abstract fun fbEntryDao(): FbEntryDao
}