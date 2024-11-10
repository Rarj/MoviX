package com.arj.genre.domain

import com.arj.genre.api.response.GenreResponse
import com.arj.genre.domain.model.GenreItemModel
import com.arj.genre.domain.model.GenreModel

fun GenreResponse.toGenres() = GenreModel(
    genres = genres.map {
        GenreItemModel(
            id = it.id,
            name = it.name
        )
    }
)
