package com.arj.genre.domain

import com.arj.genre.domain.model.GenreModel
import com.arj.network.state.MovixNetworkResult
import kotlinx.coroutines.flow.Flow

interface GenreRepository {

    suspend fun getGenres(): Flow<MovixNetworkResult<GenreModel>>

}
