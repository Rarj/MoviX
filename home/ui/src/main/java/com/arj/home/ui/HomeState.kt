package com.arj.home.ui

import androidx.paging.PagingData
import com.arj.genre.domain.GenreConst.DEFAULT_GENRE_ID
import com.arj.genre.domain.GenreConst.DEFAULT_GENRE_NAME
import com.arj.home.domain.mapper.DiscoverMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeState(
    val isLoading: Boolean? = null,
    val selectedGenre: String? = DEFAULT_GENRE_NAME,
    var selectedGenreId: String? = DEFAULT_GENRE_ID,
    val errorMessage: String? = null,
    val moviePagingDataState: Flow<PagingData<DiscoverMovie>> = emptyFlow()
)
