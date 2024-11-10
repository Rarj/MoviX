package com.arj.review.domain.usecase

import com.arj.review.domain.ReviewRepository
import javax.inject.Inject

class ReviewUseCase @Inject constructor(
    private val repository: ReviewRepository
) {

    suspend fun invoke(movieId: String) = repository.getReview(movieId = movieId)

}
