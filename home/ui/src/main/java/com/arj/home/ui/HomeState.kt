package com.arj.home.ui

import androidx.paging.PagingData
//import com.arj.genre.domain.GenreConst.DEFAULT_GENRE_ID
//import com.arj.genre.domain.GenreConst.DEFAULT_GENRE_NAME
import com.arj.home.domain.mapper.DiscoverMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeState(
    val isLoading: Boolean? = null,
    val selectedGenre: String? = "Action",
    var selectedGenreId: String? = "28",
    val errorMessage: String? = null,
    val moviePagingDataState: Flow<PagingData<DiscoverMovie>> = emptyFlow()
)
