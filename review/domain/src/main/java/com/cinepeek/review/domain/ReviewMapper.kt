package com.cinepeek.review.domain

import com.cinepeek.review.api.response.ReviewResponse
import com.cinepeek.review.domain.model.ReviewModel

fun ReviewResponse.toReview() = ReviewModel(
    id = this.id,
    author = this.author,
    content = this.content
)
