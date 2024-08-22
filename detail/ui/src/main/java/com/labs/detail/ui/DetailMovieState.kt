package com.labs.detail.ui

import androidx.compose.runtime.Immutable

@Immutable
data class DetailMovieState(
    val title: String = "",
    val posterPath: String? = null,
    val rating: String = "",
    val overview: String = "",
)
