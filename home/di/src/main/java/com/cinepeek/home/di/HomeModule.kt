package com.cinepeek.home.di

import com.cinepeek.home.api.HomeService
import com.cinepeek.home.domain.DiscoverMovieRepository
import com.cinepeek.home.domain.usecase.HomeWithDefaultGenreUseCase
import com.cinepeek.home.impl.discover.DiscoverMovieRepositoryImpl
import com.cinepeek.network.state.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Provides
    fun provideHomeService(retrofit: Retrofit): HomeService {
        return retrofit.create(HomeService::class.java)
    }

    @Provides
    fun provideDiscoverMovieRepoImpl(
        apiService: HomeService,
        @IoDispatcher dispatcher: CoroutineDispatcher,
    ): DiscoverMovieRepository {
        return DiscoverMovieRepositoryImpl(apiService, dispatcher)
    }

    @Provides
    fun provideDiscoverMovieUseCase(homeRepository: DiscoverMovieRepository) =
        HomeWithDefaultGenreUseCase(homeRepository)

}
