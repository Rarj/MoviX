package com.cinepeek.detail.impl

import com.cinepeek.detail.api.DetailMovieService
import com.cinepeek.detail.domain.DetailMovieRepository
import com.cinepeek.detail.domain.mapper.CreditsMovie
import com.cinepeek.detail.domain.mapper.DetailMovie
import com.cinepeek.detail.domain.mapper.toCreditsMovie
import com.cinepeek.detail.domain.mapper.toDetailMovie
import com.cinepeek.network.state.MovixNetworkResult
import com.cinepeek.network.state.safeCall
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
