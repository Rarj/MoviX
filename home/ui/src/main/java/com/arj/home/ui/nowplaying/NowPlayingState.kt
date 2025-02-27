package com.arj.home.ui.nowplaying

import androidx.paging.PagingData
import com.arj.home.domain.mapper.DiscoverMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class NowPlayingState(
    val isLoading: Boolean? = null,
    val errorMessage: String? = null,
    val nowPlayingMoviePagingData: Flow<PagingData<DiscoverMovie>> = emptyFlow()
)
