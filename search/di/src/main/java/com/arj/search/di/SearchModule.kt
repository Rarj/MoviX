package com.arj.search.di

import com.arj.search.api.SearchService
import com.arj.search.impl.SearchRepository
import com.arj.search.impl.SearchRepositoryImpl
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