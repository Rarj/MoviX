package com.arj.home.domain.usecase

import com.arj.genre.domain.GenreConst
import com.arj.home.domain.DiscoverMovieRepository
import javax.inject.Inject

class HomeWithDefaultGenreUseCase @Inject constructor(
    private val homeRepository: DiscoverMovieRepository,
) {
    suspend fun invoke() = homeRepository.getDiscoverMovie(GenreConst.DEFAULT_GENRE_ID)
}
