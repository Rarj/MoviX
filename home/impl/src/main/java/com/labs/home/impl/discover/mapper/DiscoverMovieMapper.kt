package com.labs.home.impl.discover.mapper

import com.labs.home.api.response.discover.Movie
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Movie.toDiscoverMovie() = DiscoverMovie(
    id = id,
    posterPath = posterPath,
    genreIds = genreIds,
    title = title,
    overview = overview,
    rating = rating,
    releaseDate = releaseDate.getDateFormatted(),
)

fun String.getDateFormatted(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
    return dateFormat?.let { SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(it) } ?: ""
}
