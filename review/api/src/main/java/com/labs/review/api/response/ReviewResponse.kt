package com.labs.review.api.response

data class ReviewResponse(
    val results: List<Review>
)

data class Review(
    val id: String,
    val author: String,
    val content: String,
)