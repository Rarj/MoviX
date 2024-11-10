package com.arj.home.domain.usecase

import com.arj.genre.domain.GenreConst
import com.arj.home.domain.DiscoverMovieRepository
import javax.inject.Inject

class HomeWithGenreUseCase @Inject constructor(
    private val homeRepository: DiscoverMovieRepository,
) {
    suspend fun invoke(genreId: String?) = homeRepository.getDiscoverMovie(
        genreId = genreId ?: GenreConst.DEFAULT_GENRE_ID
    )
}
