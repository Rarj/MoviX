package com.arj.home.domain.usecase

import com.arj.home.domain.DiscoverMovieRepository
import com.cinepeek.genre.domain.GenreConst
import javax.inject.Inject

class HomeWithDefaultGenreUseCase @Inject constructor(
    private val homeRepository: DiscoverMovieRepository,
) {
    suspend fun invoke() = homeRepository.getDiscoverMovie(GenreConst.DEFAULT_GENRE_ID)
}
