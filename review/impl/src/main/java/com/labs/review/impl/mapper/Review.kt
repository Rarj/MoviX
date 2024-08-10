package com.labs.review.impl.mapper

data class Review(
    val results: List<ReviewItem>
)

data class ReviewItem(
    val id: String,
    val author: String,
    val content: String,
)