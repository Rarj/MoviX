package com.arj.detail.impl.mapper

import com.arj.detail.api.response.DetailMovieCreditsResponse
import com.arj.detail.api.response.DetailMovieResponse
import java.text.SimpleDateFormat
import java.util.Locale

fun DetailMovieResponse.toDetailMovie() = DetailMovie(
    id = id,
    posterPath = posterPath,
    genreIds = genreIds,
    title = title,
    overview = overview,
    rating = rating,
    releaseDate = releaseDate.getDateFormatted(),
    status = status,
    genres = genres.map {
        Genre(
            id = it.id,
            name = it.name
        )
    }
)

fun DetailMovieCreditsResponse.toCreditsMovie() = CreditsMovie(
    id = id,
    casts = cast?.map {
        Cast(
            id = it.id,
            name = it.name,
            profilePath = it.profilePath,
            gender = it.gender?.getGender(),
        )
    },
    crews = crew?.map {
        Crew(
            id = it.id,
            name = it.name,
            profilePath = it.profilePath,
            gender = it.gender?.getGender(),
        )
    },
)

private fun Int.getGender() = GenderEnum.entries.find { gender -> gender.value == this }?.name

private enum class GenderEnum(val value: Int?) {
    SHE(1) {
        override val gender: String
            get() = "She"
    },
    HE(2) {
        override val gender: String
            get() = "He"
    },
    UNKNOWN(null) {
        override val gender: String
            get() = "Unknown"
    };

    abstract val gender: String
}

private fun String.getDateFormatted(): String {
    if (this.isEmpty()) return ""

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
    return dateFormat?.let { SimpleDateFormat("yyyy", Locale.getDefault()).format(it) }
        ?: ""
}
