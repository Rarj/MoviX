package com.cinepeek.home.domain

import androidx.paging.PagingData
import com.cinepeek.home.domain.mapper.DiscoverMovie
import kotlinx.coroutines.flow.Flow

interface DiscoverMovieRepository {

    suspend fun getDiscoverMovie(genreId: String?): Flow<PagingData<DiscoverMovie>>

}