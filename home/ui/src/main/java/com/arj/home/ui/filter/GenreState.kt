package com.arj.home.ui.filter

import com.arj.home.impl.genre.mapper.Genre

data class GenreState(
    val genres: List<Genre>? = null,
)