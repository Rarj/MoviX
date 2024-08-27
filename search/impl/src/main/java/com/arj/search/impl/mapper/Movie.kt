package com.arj.search.impl.mapper

data class Movie(
    val id: Int,
    val posterPath: String?,
    val genreIds: List<String>,
    val title: String,
    val overview: String,
    val rating: Double,
)
