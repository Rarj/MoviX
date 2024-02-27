package com.labs.data.repository.movie

import androidx.paging.PagingData
import com.labs.data.ViewState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getDiscoverMovie(genreIds: List<String>): Flow<PagingData<Movie>>

    fun getDetailMovie(id: String): Flow<ViewState<Movie>>

    fun getReviews(movieId: String): Flow<PagingData<Review>>
}