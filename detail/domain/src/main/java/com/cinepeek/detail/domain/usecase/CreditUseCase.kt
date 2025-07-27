package com.cinepeek.detail.domain.usecase

import com.cinepeek.detail.domain.DetailMovieRepository
import javax.inject.Inject

class CreditUseCase @Inject constructor(
    private val detailMovieRepository: DetailMovieRepository,
) {

    suspend fun invoke(movieId: String) = detailMovieRepository.getCredits(movieId)

}
