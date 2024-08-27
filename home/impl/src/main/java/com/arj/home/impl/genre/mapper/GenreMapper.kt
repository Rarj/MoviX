package com.arj.home.impl.genre.mapper

import com.arj.home.api.response.genre.Genre as GenreResponse
import com.arj.home.impl.genre.mapper.Genre as GenreModel

fun GenreResponse.toGenre() = GenreModel(
    id = id,
    name = name,
)