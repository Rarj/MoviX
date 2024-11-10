package com.arj.genre.impl

import com.arj.genre.api.GenreService
import com.arj.genre.impl.model.GenreModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val service: GenreService,
) : GenreRepository {

    override suspend fun getGenres(): Flow<GenreModel> {
        return flow {
            val response = service.getGenres()
            val genres = response.toGenres()
            emit(genres)
        }.flowOn(Dispatchers.IO)
    }

}
