package com.arj.genre.domain.usecase

import com.arj.genre.domain.GenreRepository
import javax.inject.Inject

class GenreUseCase @Inject constructor(
    private val genreRepository: GenreRepository,
) {

    suspend fun invoke() = genreRepository.getGenres()

}
