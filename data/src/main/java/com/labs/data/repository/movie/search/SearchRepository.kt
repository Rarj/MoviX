package com.labs.data.repository.movie.search

import androidx.paging.PagingData
import com.labs.data.repository.genre.Genre
import com.labs.data.repository.movie.Movie
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchMovie(
        keyword: String,
        selectedGenre: Genre,
    ): Flow<PagingData<Movie>>

}