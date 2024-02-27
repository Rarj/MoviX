package com.labs.data.repository.movie

import androidx.paging.PagingData
import com.labs.data.ViewState
import com.labs.data.di.ApiService
import com.labs.data.pagination.createPager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {

    override suspend fun getDiscoverMovie(genreIds: List<String>): Flow<PagingData<Movie>> {
        val ids = genreIds.toString()
            .replace("[", "")
            .replace("]", "")
            .replace(",", "%7C")

        return createPager { page ->
            apiService.getDiscoverMovie(ids, page)
        }.flow.flowOn(Dispatchers.IO)
    }

    override fun getDetailMovie(id: String): Flow<ViewState<Movie>> {
        return flow {
            emit(ViewState.loading())

            val response = apiService.getDetailMovie(id)
            emit(ViewState.success(response))
        }
            .flowOn(Dispatchers.IO)
            .catch {
                emit(ViewState.error(it.message.orEmpty()))
            }

    }

    override fun getReviews(movieId: String): Flow<PagingData<Review>> {
        return createPager { page ->
            apiService.getReviews(movieId, page)
        }.flow.flowOn(Dispatchers.IO)
    }

}