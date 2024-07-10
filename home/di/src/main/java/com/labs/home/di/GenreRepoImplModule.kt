package com.labs.home.di

import com.labs.data.di.NetworkQualifierHomeService
import com.labs.home.api.HomeService
import com.labs.home.impl.genre.GenreRepo
import com.labs.home.impl.genre.GenreRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class GenreRepoImplModule {

    @Provides
    fun provideGenreRepoImpl(@NetworkQualifierHomeService apiService: HomeService): GenreRepo {
        return GenreRepoImpl(apiService)
    }

}