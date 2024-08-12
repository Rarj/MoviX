package com.labs.review.impl.mapper

import com.labs.review.api.response.ReviewResponse

fun ReviewResponse.toReview() = ReviewItem(
    id = this.id,
    author = this.author,
    content = this.content
)