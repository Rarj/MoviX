package com.labs.data.repository.movie

import androidx.paging.PagingData
import com.labs.data.ViewState
import com.labs.data.repository.genre.Genre
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun setSelectedGenre(genre: Genre?)

    fun getSelectedGenre(): Genre?

    suspend fun getDiscoverMovie(): Flow<PagingData<Movie>>

    fun getDetailMovie(id: String): Flow<ViewState<Movie>>

    fun getReviews(movieId: String): Flow<PagingData<Review>>
}