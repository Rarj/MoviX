package com.labs.home.impl.discover.mapper

import com.labs.home.api.response.discover.Movie

fun Movie.toDiscoverMovie() = DiscoverMovie(
    id = id,
    posterPath = posterPath,
    genreIds = genreIds,
    title = title,
    overview = overview,
    rating = rating,
)