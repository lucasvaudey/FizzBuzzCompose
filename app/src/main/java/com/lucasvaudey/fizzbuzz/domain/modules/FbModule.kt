package com.lucasvaudey.fizzbuzz.domain.modules

import com.lucasvaudey.fizzbuzz.data.dao.FbEntryDao
import com.lucasvaudey.fizzbuzz.data.repository.FbRepositoryImpl
import com.lucasvaudey.fizzbuzz.domain.repository.FbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FbModule {

}