package com.arj.search.impl

import androidx.paging.PagingData
import com.arj.network.shared.NetworkResponse
import com.arj.network.shared.createPager
import com.arj.search.api.SearchService
import com.arj.search.impl.mapper.Movie
import com.arj.search.impl.mapper.toMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: SearchService
) : SearchRepository {

    override suspend fun searchMovie(keyword: String): Flow<PagingData<Movie>> {
        return createPager { page ->
            val response = apiService.searchMovie(keyword, page)
            val results = response.results.map { result -> result.toMovie() }

            NetworkResponse(
                page = response.page,
                totalPages = response.totalPages,
                results = results,
            )
        }.flow.flowOn(Dispatchers.IO)
    }
}