package com.arj.detail.impl

import com.arj.detail.api.DetailMovieService
import com.arj.detail.domain.mapper.CreditsMovie
import com.arj.detail.domain.mapper.DetailMovie
import com.arj.detail.domain.mapper.toCreditsMovie
import com.arj.detail.domain.mapper.toDetailMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailMovieRepositoryImpl @Inject constructor(
    private val service: DetailMovieService
) : com.arj.detail.domain.DetailMovieRepository {

    override suspend fun getDetailMovie(id: String): Flow<DetailMovie> {
        return flow {
            val response = service.getDetailMovie(id)
            emit(response.toDetailMovie())
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getCredits(id: String): Flow<CreditsMovie> {
        return flow {
            val response = service.getCredits(id)
            emit(response.toCreditsMovie())
        }.flowOn(Dispatchers.IO)
    }

}
