package com.cinepeek.detail.impl

import com.cinepeek.detail.api.DetailMovieService
import com.cinepeek.detail.domain.DetailMovieRepository
import com.cinepeek.detail.domain.mapper.CreditsMovie
import com.cinepeek.detail.domain.mapper.DetailMovie
import com.cinepeek.detail.domain.mapper.toCreditsMovie
import com.cinepeek.detail.domain.mapper.toDetailMovie
import com.cinepeek.network.state.CinepeekNetworkResult
import com.cinepeek.network.state.safeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailMovieRepositoryImpl @Inject constructor(
    private val service: DetailMovieService,
    private val dispatcher: CoroutineDispatcher,
) : DetailMovieRepository {

    override suspend fun getDetailMovie(id: String): Flow<CinepeekNetworkResult<DetailMovie>> {
        return safeCall(dispatcher) {
            service.getDetailMovie(id).toDetailMovie()
        }
    }

    override suspend fun getCredits(id: String): Flow<CinepeekNetworkResult<CreditsMovie>> {
        return safeCall(dispatcher) {
            service.getCredits(id).toCreditsMovie()
        }
    }

}
