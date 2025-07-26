package com.cinepeek.genre.domain

import com.cinepeek.genre.domain.model.GenreModel
import com.cinepeek.network.state.MovixNetworkResult
import kotlinx.coroutines.flow.Flow

interface GenreRepository {

    suspend fun getGenres(): Flow<MovixNetworkResult<GenreModel>>

}
