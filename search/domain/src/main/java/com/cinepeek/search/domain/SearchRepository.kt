package com.cinepeek.search.domain

import androidx.paging.PagingData
import com.cinepeek.search.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun searchMovie(keyword: String): Flow<PagingData<MovieModel>>

}