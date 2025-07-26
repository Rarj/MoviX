package com.cinepeek.genre.domain.usecase

import com.cinepeek.genre.domain.GenreRepository
import javax.inject.Inject

class GenreUseCase @Inject constructor(
    private val genreRepository: GenreRepository,
) {

    suspend fun invoke() = genreRepository.getGenres()

}
