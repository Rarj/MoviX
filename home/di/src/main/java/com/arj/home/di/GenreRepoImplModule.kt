package com.arj.home.di

import com.arj.home.api.HomeService
import com.arj.home.impl.genre.GenreRepo
import com.arj.home.impl.genre.GenreRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class GenreRepoImplModule {

    @Provides
    fun provideGenreRepoImpl(apiService: HomeService): GenreRepo {
        return GenreRepoImpl(apiService)
    }

}