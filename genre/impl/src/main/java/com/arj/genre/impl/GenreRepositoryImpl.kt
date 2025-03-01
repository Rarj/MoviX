package com.arj.genre.impl

import com.arj.genre.api.GenreService
import com.arj.genre.domain.GenreRepository
import com.arj.genre.domain.model.GenreModel
import com.arj.genre.domain.toGenres
import com.arj.network.state.MovixNetworkResult
import com.arj.network.state.safeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val service: GenreService,
    private val dispatcher: CoroutineDispatcher,
) : GenreRepository {

    override suspend fun getGenres(): Flow<MovixNetworkResult<GenreModel>> {
        return safeCall(dispatcher) {
            val response = service.getGenres()
            response.toGenres()
        }
    }

}
