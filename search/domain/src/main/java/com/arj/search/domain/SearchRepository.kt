package com.arj.search.domain

import androidx.paging.PagingData
import com.arj.search.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun searchMovie(keyword: String): Flow<PagingData<MovieModel>>

}