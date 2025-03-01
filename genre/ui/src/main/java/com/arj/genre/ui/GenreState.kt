package com.arj.genre.ui

import com.arj.genre.domain.model.GenreItemModel

sealed class GenreUIState {
    object Init : GenreUIState()
    object Loading : GenreUIState()
    data class Success(val state: GenreState) : GenreUIState()
    data class Error(val message: String?) : GenreUIState()
}

data class GenreState(
    val genres: List<GenreItemModel>,
)
