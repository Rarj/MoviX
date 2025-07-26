package com.arj.search.impl

import androidx.paging.PagingData
import com.arj.search.api.SearchService
import com.arj.search.domain.SearchRepository
import com.arj.search.domain.model.MovieModel
import com.arj.search.domain.toMovie
import com.cinepeek.network.shared.NetworkResponse
import com.cinepeek.network.shared.createPager
import com.cinepeek.network.state.MovixNetworkResult
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
            MovixNetworkResult.Success(result)
        }.flow.flowOn(dispatcher)
    }
}