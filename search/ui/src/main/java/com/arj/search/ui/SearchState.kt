package com.arj.search.ui

import androidx.paging.PagingData
import com.arj.search.impl.mapper.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class SearchState(
    val keyword: String? = "",
    val moviePagingItems: Flow<PagingData<Movie>> = flow { PagingData.empty<Movie>() },
)
