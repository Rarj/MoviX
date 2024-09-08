package com.arj.detail.api.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    val id: Int,
    @SerializedName("backdrop_path")
    val posterPath: String?,
    @SerializedName("genre_ids")
    val genreIds: List<String>?,
    val title: String,
    val overview: String,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("release_date")
    val releaseDate: String = "",
    val status: String = "",
    val genres: List<GenreResponse> = emptyList(),
)

data class GenreResponse(
    val id: Int,
    val name: String,
)
