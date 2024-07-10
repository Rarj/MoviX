package com.labs.data.repository.movie

import com.labs.data.di.ApiService
import com.labs.data.di.NetworkQualifierApiService
import com.labs.data.di.NetworkQualifierHomeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MovieRepositoryModule {

    @Provides
    fun provideMovieRepository(@NetworkQualifierApiService apiService: ApiService): MovieRepository {
        return MovieRepositoryImpl(apiService)
    }

}