package com.arj.detail.impl

import com.arj.detail.api.DetailMovieService
import com.arj.detail.domain.DetailMovieRepository
import com.arj.detail.domain.mapper.CreditsMovie
import com.arj.detail.domain.mapper.DetailMovie
import com.arj.detail.domain.mapper.toCreditsMovie
import com.arj.detail.domain.mapper.toDetailMovie
import com.arj.network.state.MovixNetworkResult
import com.arj.network.state.safeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailMovieRepositoryImpl @Inject constructor(
    private val service: DetailMovieService,
    private val dispatcher: CoroutineDispatcher,
) : DetailMovieRepository {

    override suspend fun getDetailMovie(id: String): Flow<MovixNetworkResult<DetailMovie>> {
        return safeCall(dispatcher) {
            service.getDetailMovie(id).toDetailMovie()
        }
    }

    override suspend fun getCredits(id: String): Flow<MovixNetworkResult<CreditsMovie>> {
        return safeCall(dispatcher) {
            service.getCredits(id).toCreditsMovie()
        }
    }

}
