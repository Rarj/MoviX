package com.arj.home.di

import com.arj.home.domain.DiscoverMovieRepository
import com.arj.home.domain.usecase.HomeWithDefaultGenreUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DiscoverMovieUseCaseModule {

    @Provides
    fun provideDiscoverMovieUseCase(homeRepository: DiscoverMovieRepository) =
        HomeWithDefaultGenreUseCase(homeRepository)

}
