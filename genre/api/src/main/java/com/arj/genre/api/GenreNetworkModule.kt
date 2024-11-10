package com.arj.genre.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class GenreNetworkModule {

    @Provides
    fun provideGenreService(retrofit: Retrofit): GenreService {
        return retrofit.create(GenreService::class.java)
    }

}
