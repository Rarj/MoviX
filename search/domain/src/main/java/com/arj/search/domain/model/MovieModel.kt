package com.arj.search.domain.model

data class MovieModel(
    val id: Int,
    val posterPath: String?,
    val genreIds: List<String>,
    val title: String,
    val overview: String,
    val rating: Double,
    val releaseDate: String,
)
