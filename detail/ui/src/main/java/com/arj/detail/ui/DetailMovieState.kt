package com.arj.detail.ui

import androidx.compose.runtime.Immutable
import com.arj.detail.domain.mapper.Cast
import com.arj.detail.domain.mapper.Crew

sealed interface DetailMovieUIState {
    data object Init : DetailMovieUIState
    data object Loading : DetailMovieUIState
    data class Success(val data: DetailMovieState) : DetailMovieUIState
    data class Error(val message: String? = null) : DetailMovieUIState
}

sealed interface CreditsMovieUIState {
    data object Init : CreditsMovieUIState
    data object Loading : CreditsMovieUIState
    data class Success(val data: CreditsMovieState) : CreditsMovieUIState
    data class Error(val message: String? = null) : CreditsMovieUIState
}

@Immutable
data class CreditsMovieState(
    val casts: List<Cast> = emptyList(),
    val crews: List<Crew> = emptyList(),
)

@Immutable
data class DetailMovieState(
    val title: String = "",
    val posterPath: String? = null,
    val rating: String = "",
    val overview: String = "",
    val releaseDate: String = "",
    val status: String = "",
    val genres: List<String> = emptyList(),
)
