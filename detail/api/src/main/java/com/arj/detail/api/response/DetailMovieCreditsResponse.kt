package com.arj.detail.api.response

import com.google.gson.annotations.SerializedName

data class DetailMovieCreditsResponse(
    val id: Int,
    val cast: List<DetailMovieCastResponse>?,
    val crew: List<DetailMovieCrewResponse>?
)

data class DetailMovieCastResponse(
    val id: Int,
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String?,
    val gender: Int?,
    @SerializedName("known_for_department")
    val department: String?,
)

data class DetailMovieCrewResponse(
    val id: Int,
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String?,
    val gender: Int?,
)
