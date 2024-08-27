package com.arj.detail.impl.mapper

import com.arj.detail.api.response.DetailMovieResponse

fun DetailMovieResponse.toDetailMovie() = DetailMovie(
    id = id,
    posterPath = posterPath,
    genreIds = genreIds,
    title = title,
    overview = overview,
    rating = rating,
)