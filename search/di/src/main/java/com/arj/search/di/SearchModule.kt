package com.arj.search.di

import com.arj.search.api.SearchService
import com.arj.search.domain.SearchRepository
import com.arj.search.domain.usecase.SearchMovieUseCase
import com.arj.search.impl.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class SearchModule {

    @Provides
    fun provideSearchRepository(service: SearchService): SearchRepository {
        return SearchRepositoryImpl(service)
    }

    @Provides
    fun provideSearchService(retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }

    @Provides
    fun provideSearchMovieUseCase(searchRepository: SearchRepository) =
        SearchMovieUseCase(searchRepository)

}
