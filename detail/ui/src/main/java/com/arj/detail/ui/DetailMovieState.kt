package com.arj.detail.ui

import androidx.compose.runtime.Immutable
import com.arj.detail.impl.mapper.Cast
import com.arj.detail.impl.mapper.Crew

@Immutable
data class DetailMovieState(
    val title: String = "",
    val posterPath: String? = null,
    val rating: String = "",
    val overview: String = "",
    val releaseDate: String = "",
    val status: String= "",
    val genres: List<String> = emptyList(),
    val casts: List<Cast> = emptyList(),
    val crews: List<Crew> = emptyList(),
)
