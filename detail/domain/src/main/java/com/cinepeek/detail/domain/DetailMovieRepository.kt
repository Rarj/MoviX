package com.cinepeek.detail.domain

import com.cinepeek.detail.domain.mapper.CreditsMovie
import com.cinepeek.detail.domain.mapper.DetailMovie
import com.cinepeek.network.state.MovixNetworkResult
import kotlinx.coroutines.flow.Flow

interface DetailMovieRepository {

    suspend fun getDetailMovie(id: String): Flow<MovixNetworkResult<DetailMovie>>

    suspend fun getCredits(id: String): Flow<MovixNetworkResult<CreditsMovie>>

}