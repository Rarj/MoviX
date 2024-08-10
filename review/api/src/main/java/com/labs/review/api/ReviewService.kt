package com.labs.review.api

import com.labs.network.shared.NetworkResponse
import com.labs.review.api.response.ReviewResponse
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