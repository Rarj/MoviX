package com.arj.detail.domain

import com.arj.detail.domain.mapper.CreditsMovie
import com.arj.detail.domain.mapper.DetailMovie
import com.arj.network.state.MovixNetworkResult
import kotlinx.coroutines.flow.Flow

interface DetailMovieRepository {

    suspend fun getDetailMovie(id: String): Flow<MovixNetworkResult<DetailMovie>>

    suspend fun getCredits(id: String): Flow<MovixNetworkResult<CreditsMovie>>

}