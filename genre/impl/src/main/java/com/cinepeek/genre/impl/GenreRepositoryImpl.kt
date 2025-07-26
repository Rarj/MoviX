package com.cinepeek.genre.impl

import com.cinepeek.genre.api.GenreService
import com.cinepeek.genre.domain.GenreRepository
import com.cinepeek.genre.domain.model.GenreModel
import com.cinepeek.genre.domain.toGenres
import com.cinepeek.network.state.MovixNetworkResult
import com.cinepeek.network.state.safeCall
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
