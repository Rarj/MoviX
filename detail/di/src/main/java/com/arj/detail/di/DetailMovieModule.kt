package com.arj.detail.di

import com.arj.detail.api.DetailMovieService
import com.arj.detail.domain.DetailMovieRepository
import com.arj.detail.domain.usecase.CreditUseCase
import com.arj.detail.domain.usecase.DetailMovieUseCase
import com.arj.detail.impl.DetailMovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class DetailMovieModule {

    @Provides
    fun provideDetailMovieRepository(
        service: DetailMovieService
    ): DetailMovieRepository {
        return DetailMovieRepositoryImpl(service)
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