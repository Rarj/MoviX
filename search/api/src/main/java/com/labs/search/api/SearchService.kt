package com.labs.search.api

import com.labs.network.shared.NetworkResponse
import com.labs.search.api.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") keywords: String,
        @Query("page") page: Int,
    ): NetworkResponse<MovieResponse>

}