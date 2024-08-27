package com.arj.home.ui

import androidx.paging.PagingData
import com.arj.home.impl.discover.mapper.DiscoverMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeState(
    val isLoading: Boolean? = null,
    val selectedGenre: String? = null,
    var selectedGenreId: String? = null,
    val errorMessage: String? = null,
    val moviePagingDataState: Flow<PagingData<DiscoverMovie>> = emptyFlow()
)
