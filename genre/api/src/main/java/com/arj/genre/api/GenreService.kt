package com.arj.genre.api

import com.arj.genre.api.response.GenreResponse
import retrofit2.http.GET

interface GenreService {

    @GET("genre/movie/list")
    suspend fun getGenres(): GenreResponse

}
