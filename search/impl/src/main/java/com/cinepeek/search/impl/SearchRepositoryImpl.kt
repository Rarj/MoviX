package com.cinepeek.search.impl

import androidx.paging.PagingData
import com.cinepeek.network.shared.NetworkResponse
import com.cinepeek.network.shared.createPager
import com.cinepeek.network.state.CinepeekNetworkResult
import com.cinepeek.search.api.SearchService
import com.cinepeek.search.domain.SearchRepository
import com.cinepeek.search.domain.model.MovieModel
import com.cinepeek.search.domain.toMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: SearchService,
    private val dispatcher: CoroutineDispatcher,
) : SearchRepository {

    override suspend fun searchMovie(keyword: String): Flow<PagingData<MovieModel>> {
        return createPager { page ->
            val response = apiService.searchMovie(keyword, page)
            val results = response.results.map { result -> result.toMovie() }

            val result = NetworkResponse(
                page = response.page,
                totalPages = response.totalPages,
                results = results,
            )
            CinepeekNetworkResult.Success(result)
        }.flow.flowOn(dispatcher)
    }
}