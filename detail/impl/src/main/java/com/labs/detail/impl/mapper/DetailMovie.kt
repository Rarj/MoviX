package com.labs.detail.impl.mapper

data class DetailMovie(
    val id: Int,
    val posterPath: String?,
    val genreIds: List<String>?,
    val title: String,
    val overview: String,
    val rating: Double,
)
