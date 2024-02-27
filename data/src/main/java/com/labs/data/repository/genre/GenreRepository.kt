package com.labs.data.repository.genre

import com.labs.data.ViewState
import kotlinx.coroutines.flow.Flow

interface GenreRepository {

    fun getGenres(): Flow<ViewState<List<Genre>>>

}
