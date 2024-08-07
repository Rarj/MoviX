package com.labs.home.impl.genre

import com.labs.data.ViewState
import com.labs.home.impl.genre.mapper.Genre
import kotlinx.coroutines.flow.Flow

interface GenreRepo {

    fun getGenres(): Flow<ViewState<List<Genre>>>

}
