package com.labs.detail.di

import com.labs.data.di.NetworkQualifierDetailMovieService
import com.labs.detail.api.DetailMovieService
import com.labs.detail.impl.DetailMovieRepository
import com.labs.detail.impl.DetailMovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DetailMovieModule {

    @Provides
    fun provideDetailMovieRepository(
        @NetworkQualifierDetailMovieService service: DetailMovieService
    ): DetailMovieRepository {
        return DetailMovieRepositoryImpl(service)
    }

}