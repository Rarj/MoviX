package com.arj.detail.di

import com.arj.detail.api.DetailMovieService
import com.arj.detail.impl.DetailMovieRepository
import com.arj.detail.impl.DetailMovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DetailMovieModule {

    @Provides
    fun provideDetailMovieRepository(
        service: DetailMovieService
    ): DetailMovieRepository {
        return DetailMovieRepositoryImpl(service)
    }

}