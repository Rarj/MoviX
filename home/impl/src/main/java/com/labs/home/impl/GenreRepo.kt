package com.labs.home.impl

import com.labs.data.ViewState
import com.labs.home.api.response.genre.Genre
import kotlinx.coroutines.flow.Flow

interface GenreRepo {

    fun getGenres(): Flow<ViewState<List<Genre>>>

}
