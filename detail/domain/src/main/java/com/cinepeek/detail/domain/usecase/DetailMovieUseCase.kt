package com.cinepeek.detail.domain.usecase

import com.cinepeek.detail.domain.DetailMovieRepository
import javax.inject.Inject

class DetailMovieUseCase @Inject constructor(
    private val detailMovieRepository: DetailMovieRepository,
) {

    suspend fun invoke(movieId: String) = detailMovieRepository.getDetailMovie(id = movieId)

}
