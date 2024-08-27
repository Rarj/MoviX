package com.arj.home.impl.discover

import androidx.paging.PagingData
import com.arj.home.impl.discover.mapper.DiscoverMovie
import kotlinx.coroutines.flow.Flow

interface DiscoverMovieRepository {

    suspend fun getDiscoverMovie(genreId: String?): Flow<PagingData<DiscoverMovie>>

}