package com.arj.detail.di

import com.arj.detail.api.DetailMovieService
import com.arj.detail.domain.DetailMovieRepository
import com.arj.detail.domain.usecase.CreditUseCase
import com.arj.detail.domain.usecase.DetailMovieUseCase
import com.arj.detail.impl.DetailMovieRepositoryImpl
import com.arj.network.state.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class DetailMovieModule {

    @Provides
    fun provideDetailMovieRepository(
        service: DetailMovieService,
        @IoDispatcher dispatcher: CoroutineDispatcher,
    ): DetailMovieRepository {
        return DetailMovieRepositoryImpl(service, dispatcher)
    }

    @Provides
    fun provideDetailMovieService(retrofit: Retrofit): DetailMovieService {
        return retrofit.create(DetailMovieService::class.java)
    }

    @Provides
    fun provideDetailMovieUseCase(detailMovieRepository: DetailMovieRepository) =
        DetailMovieUseCase(detailMovieRepository)

    @Provides
    fun provideCreditsUseCase(detailMovieRepository: DetailMovieRepository) =
        CreditUseCase(detailMovieRepository)
}