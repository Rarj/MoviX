package com.labs.detail.ui

import androidx.compose.runtime.Immutable

@Immutable
data class DetailMovieState(
    val title: String = "",
    val posterUrl: String = "",
    val rating: String = "",
    val overview: String = "",
)
