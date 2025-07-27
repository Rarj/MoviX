package com.cinepeek.search.di

import com.cinepeek.search.api.SearchService
import com.cinepeek.search.domain.SearchRepository
import com.cinepeek.search.domain.usecase.SearchMovieUseCase
import com.cinepeek.search.impl.SearchRepositoryImpl
import com.cinepeek.network.state.IoDispatcher
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
