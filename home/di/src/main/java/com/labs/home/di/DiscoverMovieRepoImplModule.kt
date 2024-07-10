package com.labs.home.di

import com.labs.data.di.NetworkQualifierHomeService
import com.labs.home.api.HomeService
import com.labs.home.impl.discover.MovieRepository
import com.labs.home.impl.discover.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DiscoverMovieRepoImplModule {

    @Provides
    fun provideDiscoverMovieRepoImpl(@NetworkQualifierHomeService apiService: HomeService): MovieRepository {
        return MovieRepositoryImpl(apiService)

    }

}