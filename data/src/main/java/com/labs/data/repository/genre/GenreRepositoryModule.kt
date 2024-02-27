package com.labs.data.repository.genre

import com.labs.data.di.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class GenreRepositoryModule {

    @Provides
    fun provideGenreRepository(apiService: ApiService) : GenreRepository {
        return GenreRepositoryImpl(apiService)
    }

}