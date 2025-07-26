package com.arj.review.api

import com.arj.review.api.response.ReviewResponse
import com.cinepeek.network.shared.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewService {

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviews(
        @Path("movie_id") movieId: String,
        @Query("page") page: Int,
    ): NetworkResponse<ReviewResponse>

}