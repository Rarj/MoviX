package com.arj.genre.di

import com.arj.genre.api.GenreService
import com.arj.genre.domain.GenreRepository
import com.arj.genre.impl.GenreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class GenreModule {

    @Provides
    fun provideGenreService(retrofit: Retrofit): GenreService {
        return retrofit.create(GenreService::class.java)
    }

    @Provides
    fun provideGenreRepository(
        service: GenreService,
    ): GenreRepository {
        return GenreRepositoryImpl(service)
    }

}
