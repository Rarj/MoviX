package com.labs.home.impl.discover

import androidx.paging.PagingData
import com.labs.home.impl.discover.mapper.DiscoverMovie
import com.labs.home.impl.genre.mapper.Genre
import kotlinx.coroutines.flow.Flow

interface DiscoverMovieRepository {

    suspend fun getDiscoverMovie(genreId: String?): Flow<PagingData<DiscoverMovie>>

}