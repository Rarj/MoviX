package com.labs.review.impl.mapper

import com.labs.review.api.response.ReviewResponse


fun ReviewResponse.toReview() = Review(
    results = this.results.map {
        ReviewItem(
            id = it.id,
            author = it.author,
            content = it.content
        )
    }
)