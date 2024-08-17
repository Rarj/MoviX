package com.labs.detail.impl

import com.labs.detail.api.DetailMovieService
import com.labs.detail.impl.mapper.DetailMovie
import com.labs.detail.impl.mapper.toDetailMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailMovieRepositoryImpl @Inject constructor(
    private val service: DetailMovieService
) : DetailMovieRepository {

    override fun getDetailMovie(id: String): Flow<DetailMovie> {
        return flow {
            val response = service.getDetailMovie(id)
            emit(response.toDetailMovie())
        }.flowOn(Dispatchers.IO)
    }
}
