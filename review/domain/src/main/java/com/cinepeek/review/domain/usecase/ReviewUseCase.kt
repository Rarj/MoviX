package com.cinepeek.review.domain.usecase

import com.cinepeek.review.domain.ReviewRepository
import javax.inject.Inject

class ReviewUseCase @Inject constructor(
    private val repository: ReviewRepository
) {

    suspend fun invoke(movieId: String) = repository.getReview(movieId = movieId)

}
