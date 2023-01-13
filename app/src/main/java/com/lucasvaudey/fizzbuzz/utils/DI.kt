package com.lucasvaudey.fizzbuzz.utils

import android.content.Context
import androidx.room.Room
import com.lucasvaudey.fizzbuzz.data.dao.FbEntryDao
import com.lucasvaudey.fizzbuzz.data.database.Fbdb
import com.lucasvaudey.fizzbuzz.data.repository.FbRepositoryImpl
import com.lucasvaudey.fizzbuzz.domain.repository.FbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FbEntryDaoModule {
    @Provides
    @Singleton
    fun provideFbdb(@ApplicationContext context: Context): Fbdb =
        Room.databaseBuilder(context, Fbdb::class.java, "fbdb").build()

    @Provides
    fun provideFbEntryDao(db: Fbdb): FbEntryDao = db.fbEntryDao()

    @Provides
    fun provideFbRepository(dao: FbEntryDao): FbRepository = FbRepositoryImpl(dao)
}
