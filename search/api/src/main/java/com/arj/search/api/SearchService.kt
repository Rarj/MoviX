package com.arj.search.api

import com.arj.search.api.response.MovieResponse
import com.cinepeek.network.shared.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") keywords: String,
        @Query("page") page: Int,
    ): NetworkResponse<MovieResponse>

}