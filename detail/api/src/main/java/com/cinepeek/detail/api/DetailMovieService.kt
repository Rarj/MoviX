package com.cinepeek.detail.api

import com.cinepeek.detail.api.response.DetailMovieCreditsResponse
import com.cinepeek.detail.api.response.DetailMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailMovieService {

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: String
    ): DetailMovieResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movieId: String
    ): DetailMovieCreditsResponse

}