package com.labs.home.impl.genre.mapper

import com.labs.home.api.response.genre.Genre as GenreResponse
import com.labs.home.impl.genre.mapper.Genre as GenreModel

fun GenreResponse.toGenre() = GenreModel(
    id = id,
    name = name,
)