package com.labs.search.impl

import androidx.paging.PagingData
import com.labs.search.impl.mapper.Movie
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun searchMovie(keyword: String): Flow<PagingData<Movie>>

}