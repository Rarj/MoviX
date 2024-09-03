package com.arj.detail.impl.mapper

import com.arj.detail.api.response.DetailMovieCreditsResponse
import com.arj.detail.api.response.DetailMovieResponse

fun DetailMovieResponse.toDetailMovie() = DetailMovie(
    id = id,
    posterPath = posterPath,
    genreIds = genreIds,
    title = title,
    overview = overview,
    rating = rating,
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