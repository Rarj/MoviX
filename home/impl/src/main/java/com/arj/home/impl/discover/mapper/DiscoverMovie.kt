package com.arj.home.impl.discover.mapper

data class DiscoverMovie(
    val id: Int,
    val posterPath: String?,
    val genreIds: List<String>,
    val title: String,
    val overview: String,
    val rating: Double,
    val releaseDate: String,
)
