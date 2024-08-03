package com.labs.detail.impl

import com.labs.data.ViewState
import com.labs.detail.impl.mapper.DetailMovie
import kotlinx.coroutines.flow.Flow

interface DetailMovieRepository {

    fun getDetailMovie(id: String): Flow<ViewState<DetailMovie>>

}