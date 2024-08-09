package com.labs.detail.impl

import com.labs.data.ViewState
import com.labs.detail.api.DetailMovieService
import com.labs.detail.impl.mapper.DetailMovie
import com.labs.detail.impl.mapper.toDetailMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailMovieRepositoryImpl @Inject constructor(
    private val service: DetailMovieService
) : DetailMovieRepository {

    override fun getDetailMovie(id: String): Flow<ViewState<DetailMovie>> {
        return flow {
            emit(ViewState.loading())

            val response = service.getDetailMovie(id)
            val result = response.toDetailMovie()
            emit(ViewState.success(result))
        }.flowOn(Dispatchers.IO)
            .catch {
                emit(ViewState.error(it.message.orEmpty()))
            }

    }
}
