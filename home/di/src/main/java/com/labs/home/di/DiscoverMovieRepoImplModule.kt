package com.labs.home.di

import com.labs.data.di.NetworkQualifierHomeService
import com.labs.home.api.HomeService
import com.labs.home.impl.discover.DiscoverMovieRepository
import com.labs.home.impl.discover.DiscoverMovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DiscoverMovieRepoImplModule {

    @Provides
    fun provideDiscoverMovieRepoImpl(@NetworkQualifierHomeService apiService: HomeService): DiscoverMovieRepository {
        return DiscoverMovieRepositoryImpl(apiService)

    }

}