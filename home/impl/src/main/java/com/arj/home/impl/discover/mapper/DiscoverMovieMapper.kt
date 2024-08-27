package com.arj.home.impl.discover.mapper

import com.arj.home.api.response.discover.Movie

fun Movie.toDiscoverMovie() = DiscoverMovie(
    id = id,
    posterPath = posterPath,
    genreIds = genreIds,
    title = title,
    overview = overview,
    rating = rating,
)