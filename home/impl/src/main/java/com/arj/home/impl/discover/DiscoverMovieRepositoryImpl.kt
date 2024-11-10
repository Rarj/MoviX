package com.arj.home.impl.discover

import androidx.paging.PagingData
import com.arj.home.api.HomeService
import com.arj.home.domain.DiscoverMovieRepository
import com.arj.home.domain.mapper.DiscoverMovie
import com.arj.home.domain.mapper.toDiscoverMovie
import com.arj.network.shared.NetworkResponse
import com.arj.network.shared.createPager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DiscoverMovieRepositoryImpl @Inject constructor(
    private val apiService: HomeService
) : DiscoverMovieRepository {

    override suspend fun getDiscoverMovie(genreId: String?): Flow<PagingData<DiscoverMovie>> {
        return createPager { page ->
            val ids = listOf(genreId.orEmpty()).toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "%7C")
            val response = apiService.getDiscoverMovie(ids, page)
            val movies = response.results.map { it.toDiscoverMovie() }

            NetworkResponse(
                page = response.page,
                totalPages = response.totalPages,
                results = movies
            )
        }.flow.flowOn(Dispatchers.IO)
    }

}