package com.arj.detail.domain.mapper

data class DetailMovie(
    val id: Int,
    val posterPath: String?,
    val genreIds: List<String>?,
    val title: String,
    val overview: String,
    val rating: Double,
    val releaseDate: String = "",
    val status: String = "",
    val genres: List<Genre>? = emptyList()
)

data class Genre(
    val id: Int,
    val name: String,
)