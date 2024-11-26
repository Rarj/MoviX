package com.arj.search.domain.usecase

import com.arj.search.domain.SearchRepository
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
) {

    suspend fun invoke(keyword: String) = searchRepository.searchMovie(keyword = keyword)

}
