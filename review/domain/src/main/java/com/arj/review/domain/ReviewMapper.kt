package com.arj.review.domain

import com.arj.review.api.response.ReviewResponse
import com.arj.review.domain.model.ReviewModel

fun ReviewResponse.toReview() = ReviewModel(
    id = this.id,
    author = this.author,
    content = this.content
)
