package com.cinepeek.genre.api

import com.cinepeek.genre.api.response.GenreResponse
import retrofit2.http.GET

interface GenreService {

    @GET("genre/movie/list")
    suspend fun getGenres(): GenreResponse

}
