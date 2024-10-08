package com.arj.home.impl.genre

import com.arj.home.api.HomeService
import com.arj.home.impl.genre.mapper.Genre
import com.arj.home.impl.genre.mapper.toGenre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenreRepoImpl @Inject constructor(
    private val service: HomeService
) : GenreRepo {

    override fun getGenres(): Flow<List<Genre>> {
        return flow {
            val response = service.getGenre()
            val genres = response.genres.map {
                it.toGenre()
            }
            emit(genres)
        }.flowOn(Dispatchers.IO)
    }

}
