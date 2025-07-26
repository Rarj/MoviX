package com.cinepeek.home.api

import com.cinepeek.home.api.response.discover.Movie
import com.cinepeek.network.shared.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query("with_genres") genreIds: String,
        @Query("page") page: Int,
    ): NetworkResponse<Movie>

}