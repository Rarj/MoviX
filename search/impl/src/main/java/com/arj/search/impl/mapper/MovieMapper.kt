package com.arj.search.impl.mapper

import com.arj.search.api.response.MovieResponse

fun MovieResponse.toMovie() = Movie(
    id = id,
    posterPath = posterPath,
    genreIds = genreIds,
    title = title,
    overview = overview,
    rating = rating,
)
