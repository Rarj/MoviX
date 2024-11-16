package com.arj.home.impl.discover

import androidx.paging.PagingData
import com.arj.home.api.HomeService
import com.arj.home.domain.DiscoverMovieRepository
import com.arj.home.domain.mapper.DiscoverMovie
import com.arj.home.domain.mapper.toDiscoverMovie
import com.arj.network.shared.NetworkResponse
import com.arj.network.shared.createPager
import com.arj.network.state.MovixNetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DiscoverMovieRepositoryImpl @Inject constructor(
    private val apiService: HomeService,
    private val dispatcher: CoroutineDispatcher
) : DiscoverMovieRepository {

    override suspend fun getDiscoverMovie(genreId: String?): Flow<PagingData<DiscoverMovie>> {
        return createPager { page ->
            val response = apiService.getDiscoverMovie(genreId.orEmpty(), page)
            val movies = response.results.map { it.toDiscoverMovie() }

            val result = NetworkResponse(
                page = response.page,
                totalPages = response.totalPages,
                results = movies
            )

            MovixNetworkResult.Success(result)
        }.flow.flowOn(dispatcher)
    }

}
