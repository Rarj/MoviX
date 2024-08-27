package com.arj.home.impl.discover.mapper

data class DiscoverMovie(
    var id: Int,
    var posterPath: String?,
    var genreIds: List<String>,
    var title: String,
    var overview: String,
    var rating: Double,
)
