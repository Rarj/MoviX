package com.labs.search.impl.mapper

import com.labs.search.api.response.MovieResponse
import java.text.SimpleDateFormat
import java.util.Locale

fun MovieResponse.toMovie() = Movie(
    id = id,
    posterPath = posterPath,
    genreIds = genreIds,
    title = title,
    overview = overview,
    rating = rating,
    releaseDate = releaseDate.getDateFormatted(),
)

fun String.getDateFormatted(): String {
    if (this.isEmpty()) return ""

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
    return dateFormat?.let { SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(it) } ?: ""
}
