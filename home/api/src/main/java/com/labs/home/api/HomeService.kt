package com.labs.home.api

import com.labs.home.api.response.discover.Movie
import com.labs.home.api.response.genre.GenreResponse
import com.labs.network.shared.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    @GET("genre/movie/list")
    suspend fun getGenre(): GenreResponse

    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query("with_genres") genreIds: String,
        @Query("page") page: Int,
    ): NetworkResponse<Movie>

}