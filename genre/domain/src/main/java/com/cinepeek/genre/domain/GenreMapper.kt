package com.cinepeek.genre.domain

import com.cinepeek.genre.api.response.GenreResponse
import com.cinepeek.genre.domain.model.GenreItemModel
import com.cinepeek.genre.domain.model.GenreModel

fun GenreResponse.toGenres() = GenreModel(
    genres = genres.map {
        GenreItemModel(
            id = it.id,
            name = it.name
        )
    }
)
