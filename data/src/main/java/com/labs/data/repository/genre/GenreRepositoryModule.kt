package com.labs.data.repository.genre

import com.labs.data.di.ApiService
import com.labs.data.di.NetworkQualifierApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class GenreRepositoryModule {

    @Provides
    fun provideGenreRepository(@NetworkQualifierApiService apiService: ApiService) : GenreRepository {
        return GenreRepositoryImpl(apiService)
    }

}