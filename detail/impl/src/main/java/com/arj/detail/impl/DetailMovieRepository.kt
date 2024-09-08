package com.arj.detail.impl

import com.arj.detail.impl.mapper.CreditsMovie
import com.arj.detail.impl.mapper.DetailMovie
import kotlinx.coroutines.flow.Flow

interface DetailMovieRepository {

    fun getDetailMovie(id: String): Flow<DetailMovie>

    fun getCredits(id: String): Flow<CreditsMovie>

}