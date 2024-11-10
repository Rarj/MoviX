package com.arj.genre.impl

import com.arj.genre.api.GenreService
import com.arj.genre.domain.GenreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class GenreRepoModule {

    @Provides
    fun provideGenreRepository(
        service: GenreService,
    ): GenreRepository {
        return GenreRepositoryImpl(service)
    }

}
