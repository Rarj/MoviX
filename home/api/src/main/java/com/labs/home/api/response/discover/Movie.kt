package com.labs.home.api.response.discover

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("genre_ids")
    val genreIds: List<String>,
    val title: String,
    val overview: String,
    @SerializedName("vote_average")
    val rating: Double,
)
