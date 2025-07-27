package com.cinepeek.home.domain.usecase

import com.cinepeek.genre.domain.GenreConst
import com.cinepeek.home.domain.DiscoverMovieRepository
import javax.inject.Inject

class HomeWithGenreUseCase @Inject constructor(
    private val homeRepository: DiscoverMovieRepository,
) {
    suspend fun invoke(genreId: String?) = homeRepository.getDiscoverMovie(
        genreId = genreId ?: GenreConst.DEFAULT_GENRE_ID
    )
}
