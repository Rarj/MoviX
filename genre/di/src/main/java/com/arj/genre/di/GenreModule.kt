package com.arj.genre.di

import com.arj.genre.api.GenreService
import com.arj.genre.domain.GenreRepository
import com.arj.genre.domain.usecase.GenreUseCase
import com.arj.genre.impl.GenreRepositoryImpl
import com.arj.network.state.IoDispatcher
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
