package com.labs.home.api

import com.labs.home.api.response.GenreResponse
import retrofit2.http.GET

interface HomeService {

    @GET("genre/movie/list")
    suspend fun getGenre(): GenreResponse

}