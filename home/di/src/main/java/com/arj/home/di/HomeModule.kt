package com.arj.home.di

import com.arj.home.api.HomeService
import com.arj.home.domain.DiscoverMovieRepository
import com.arj.home.domain.usecase.HomeWithDefaultGenreUseCase
import com.arj.home.impl.discover.DiscoverMovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Provides
    fun provideHomeService(retrofit: Retrofit): HomeService {
        return retrofit.create(HomeService::class.java)
    }

    @Provides
    fun provideDiscoverMovieRepoImpl(apiService: HomeService): DiscoverMovieRepository {
        return DiscoverMovieRepositoryImpl(apiService)

    }

    @Provides
    fun provideDiscoverMovieUseCase(homeRepository: DiscoverMovieRepository) =
        HomeWithDefaultGenreUseCase(homeRepository)

}
