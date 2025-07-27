package com.cinepeek.home.domain.usecase

import com.cinepeek.genre.domain.GenreConst
import com.cinepeek.home.domain.DiscoverMovieRepository
import javax.inject.Inject

class HomeWithDefaultGenreUseCase @Inject constructor(
    private val homeRepository: DiscoverMovieRepository,
) {
    suspend fun invoke() = homeRepository.getDiscoverMovie(GenreConst.DEFAULT_GENRE_ID)
}
