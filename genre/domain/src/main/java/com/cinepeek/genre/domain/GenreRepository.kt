package com.cinepeek.genre.domain

import com.cinepeek.genre.domain.model.GenreModel
import com.cinepeek.network.state.CinepeekNetworkResult
import kotlinx.coroutines.flow.Flow

interface GenreRepository {

    suspend fun getGenres(): Flow<CinepeekNetworkResult<GenreModel>>

}
