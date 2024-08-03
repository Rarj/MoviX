package com.labs.detail.api

import com.labs.detail.api.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailService {

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: String
    ): MovieResponse

}