package com.cinepeek.genre.di

import com.cinepeek.genre.api.GenreService
import com.cinepeek.genre.domain.GenreRepository
import com.cinepeek.genre.domain.usecase.GenreUseCase
import com.cinepeek.genre.impl.GenreRepositoryImpl
import com.cinepeek.network.state.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
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
        @IoDispatcher dispatcher: CoroutineDispatcher,
    ): GenreRepository {
        return GenreRepositoryImpl(service, dispatcher)
    }

    @Provides
    fun provideGenreUseCase(genreRepository: GenreRepository) = GenreUseCase(genreRepository)

}
