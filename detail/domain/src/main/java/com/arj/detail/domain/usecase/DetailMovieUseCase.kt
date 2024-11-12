package com.arj.detail.domain.usecase

import com.arj.detail.domain.DetailMovieRepository
import javax.inject.Inject

class DetailMovieUseCase @Inject constructor(
    private val detailMovieRepository: DetailMovieRepository,
) {

    suspend fun invoke(movieId: String) = detailMovieRepository.getDetailMovie(id = movieId)

}
