package com.labs.home.ui.filter

import com.labs.home.impl.genre.mapper.Genre

data class GenreState(
    val genres: List<Genre>? = null,
)