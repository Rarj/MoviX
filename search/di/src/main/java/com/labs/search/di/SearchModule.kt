package com.labs.search.di

import com.labs.search.api.SearchService
import com.labs.search.impl.SearchRepository
import com.labs.search.impl.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

class SearchModule {

    @Provides
    fun provideSearchRepository(service: SearchService): SearchRepository {
        return SearchRepositoryImpl(service)
    }

}