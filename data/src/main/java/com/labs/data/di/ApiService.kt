package com.labs.data.di

import com.labs.data.repository.genre.GenreResponse
import com.labs.data.repository.movie.Movie
import com.labs.data.repository.movie.Review
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("genre/movie/list")
    suspend fun getGenre(): GenreResponse

    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query("with_genres") genreIds: String,
        @Query("page") page: Int,
    ): NetworkResponse<Movie>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") keywords: String,
        @Query("page") page: Int,
    ): NetworkResponse<Movie>

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: String
    ): Movie

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviews(
        @Path("movie_id") movieId: String,
        @Query("page") page: Int,
    ): NetworkResponse<Review>


}