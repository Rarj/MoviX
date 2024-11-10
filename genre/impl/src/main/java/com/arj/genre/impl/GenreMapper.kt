package com.arj.genre.impl

import com.arj.genre.api.response.GenreResponse
import com.arj.genre.impl.model.GenreItemModel
import com.arj.genre.impl.model.GenreModel

internal fun GenreResponse.toGenres() = GenreModel(
    genres = genres.map {
        GenreItemModel(
            id = it.id,
            name = it.name
        )
    }
)
