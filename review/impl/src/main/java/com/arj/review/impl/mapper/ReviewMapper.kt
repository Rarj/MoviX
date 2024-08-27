package com.arj.review.impl.mapper

import com.arj.review.api.response.ReviewResponse

fun ReviewResponse.toReview() = ReviewItem(
    id = this.id,
    author = this.author,
    content = this.content
)