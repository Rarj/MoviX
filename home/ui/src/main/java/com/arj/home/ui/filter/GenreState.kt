package com.arj.home.ui.filter

import com.arj.genre.domain.model.GenreItemModel

data class GenreState(
    val genres: List<GenreItemModel>? = null,
)