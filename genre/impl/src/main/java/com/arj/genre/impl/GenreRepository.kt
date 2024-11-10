package com.arj.genre.impl

import com.arj.genre.impl.model.GenreModel
import kotlinx.coroutines.flow.Flow

interface GenreRepository {

    suspend fun getGenres(): Flow<GenreModel>

}
