package com.arj.home.impl.genre

import com.arj.home.impl.genre.mapper.Genre
import kotlinx.coroutines.flow.Flow

interface GenreRepo {

    fun getGenres(): Flow<List<Genre>>

}
