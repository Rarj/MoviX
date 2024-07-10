package com.labs.data.repository.movie.search

import com.labs.data.di.ApiService
import com.labs.data.di.NetworkQualifierApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SearchRepositoryModule {

    @Provides
    fun providesSearchRepository(@NetworkQualifierApiService apiService: ApiService): SearchRepository {
        return SearchRepositoryImpl(apiService)
    }

}