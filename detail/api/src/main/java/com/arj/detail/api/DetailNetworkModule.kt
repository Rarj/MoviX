package com.arj.detail.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class DetailNetworkModule {

    @Provides
    fun provideDetailMovieService(retrofit: Retrofit): DetailMovieService {
        return retrofit.create(DetailMovieService::class.java)
    }

}

