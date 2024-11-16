package com.arj.search.di

import com.arj.network.shared.IoDispatcher
import com.arj.search.api.SearchService
import com.arj.search.domain.SearchRepository
import com.arj.search.domain.usecase.SearchMovieUseCase
import com.arj.search.impl.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class SearchModule {

    @Provides
    fun provideSearchRepository(
        service: SearchService,
        @IoDispatcher dispatcher: CoroutineDispatcher,
    ): SearchRepository {
        return SearchRepositoryImpl(service, dispatcher)
    }

    @Provides
    fun provideSearchService(retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }

    @Provides
    fun provideSearchMovieUseCase(searchRepository: SearchRepository) =
        SearchMovieUseCase(searchRepository)

}
