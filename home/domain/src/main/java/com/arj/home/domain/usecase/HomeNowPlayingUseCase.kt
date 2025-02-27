package com.arj.home.domain.usecase

import com.arj.home.domain.DiscoverMovieRepository
import javax.inject.Inject

class HomeNowPlayingUseCase @Inject constructor(
    private val homeRepository: DiscoverMovieRepository,
) {
    suspend fun invoke() = homeRepository.getNowPlayingMovie()
}
