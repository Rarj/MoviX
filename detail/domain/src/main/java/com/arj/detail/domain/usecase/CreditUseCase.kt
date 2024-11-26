package com.arj.detail.domain.usecase

import com.arj.detail.domain.DetailMovieRepository
import javax.inject.Inject

class CreditUseCase @Inject constructor(
    private val detailMovieRepository: DetailMovieRepository,
) {

    suspend fun invoke(movieId: String) = detailMovieRepository.getCredits(movieId)

}
