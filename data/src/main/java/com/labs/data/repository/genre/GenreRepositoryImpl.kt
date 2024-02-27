package com.labs.data.repository.genre

import com.labs.data.ViewState
import com.labs.data.di.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val service: ApiService
) : GenreRepository {

    override fun getGenres(): Flow<ViewState<List<Genre>>> {
        return flow {
            emit(ViewState.loading())

            val response = service.getGenre()
            emit(ViewState.success(response.genres))
        }
            .flowOn(Dispatchers.IO)
            .catch {
                emit(ViewState.error(it.message.toString()))
            }
    }

}
