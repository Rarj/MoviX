package com.arj.search.impl

import androidx.paging.PagingData
import com.arj.search.impl.mapper.Movie
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun searchMovie(keyword: String): Flow<PagingData<Movie>>

}