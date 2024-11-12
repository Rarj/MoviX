package com.arj.detail.domain

import com.arj.detail.domain.mapper.CreditsMovie
import com.arj.detail.domain.mapper.DetailMovie
import kotlinx.coroutines.flow.Flow

interface DetailMovieRepository {

    suspend fun getDetailMovie(id: String): Flow<DetailMovie>

    suspend fun getCredits(id: String): Flow<CreditsMovie>

}