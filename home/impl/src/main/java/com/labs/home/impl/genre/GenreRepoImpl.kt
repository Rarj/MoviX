package com.labs.home.impl.genre

import com.labs.data.ViewState
import com.labs.home.api.HomeService
import com.labs.home.impl.genre.mapper.Genre
import com.labs.home.impl.genre.mapper.toGenre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenreRepoImpl @Inject constructor(
    private val service: HomeService
) : GenreRepo {

    override fun getGenres(): Flow<ViewState<List<Genre>>> {
        return flow {
            emit(ViewState.loading())

            val response = service.getGenre()
            val genres = response.genres.map {
                it.toGenre()
            }
            emit(ViewState.success(genres))
        }
            .flowOn(Dispatchers.IO)
            .catch {
                emit(ViewState.error(it.message.toString()))
            }
    }

}
