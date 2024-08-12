package com.labs.detail.impl.mapper

import com.labs.detail.api.response.DetailMovieResponse

fun DetailMovieResponse.toDetailMovie() = DetailMovie(
    id = id,
    posterPath = posterPath,
    genreIds = genreIds,
    title = title,
    overview = overview,
    rating = rating,
)