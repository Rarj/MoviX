package com.labs.home.impl.discover

import androidx.paging.PagingData
import com.labs.data.repository.genre.Genre
import com.labs.home.api.response.discover.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun setSelectedGenre(genre: Genre?)

    fun getSelectedGenre(): Genre?

    suspend fun getDiscoverMovie(): Flow<PagingData<Movie>>

}