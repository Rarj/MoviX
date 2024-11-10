package com.arj.genre.api.response

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres")
    val genres: List<GenreItemResponse>,
)
