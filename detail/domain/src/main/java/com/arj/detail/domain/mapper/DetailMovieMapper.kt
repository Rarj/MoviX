package com.arj.detail.domain.mapper

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
            department = it.department,
        )
    },
    crews = crew?.map {
        Crew(
            id = it.id,
            name = it.name,
            profilePath = it.profilePath,
            gender = it.gender?.getGender(),
            department = it.department,
        )
    },
)

private fun Int.getGender() = GenderEnum.entries.find { gender -> gender.value == this }?.gender

private enum class GenderEnum(val value: Int?) {
    NOT_SPECIFIED(0) {
        override val gender: String
            get() = "Not Specified"
    },
    SHE(1) {
        override val gender: String
            get() = "She"
    },
    HE(2) {
        override val gender: String
            get() = "He"
    },
    NON_BINARY(3) {
        override val gender: String
            get() = "Non-binary"
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
