package com.arj.home.di

import com.arj.home.api.HomeService
import com.arj.home.impl.discover.DiscoverMovieRepository
import com.arj.home.impl.discover.DiscoverMovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DiscoverMovieRepoImplModule {

    @Provides
    fun provideDiscoverMovieRepoImpl(apiService: HomeService): DiscoverMovieRepository {
        return DiscoverMovieRepositoryImpl(apiService)

    }

}