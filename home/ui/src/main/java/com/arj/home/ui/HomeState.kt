package com.arj.home.ui

import androidx.paging.PagingData
import com.arj.home.domain.mapper.DiscoverMovie
import com.cinepeek.genre.domain.GenreConst.DEFAULT_GENRE_ID
import com.cinepeek.genre.domain.GenreConst.DEFAULT_GENRE_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeState(
    val isLoading: Boolean? = null,
    val selectedGenre: String? = DEFAULT_GENRE_NAME,
    var selectedGenreId: String? = DEFAULT_GENRE_ID,
    val errorMessage: String? = null,
    val moviePagingDataState: Flow<PagingData<DiscoverMovie>> = emptyFlow()
)
