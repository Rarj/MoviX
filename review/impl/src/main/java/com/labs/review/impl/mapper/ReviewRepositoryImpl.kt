package com.labs.review.impl.mapper

import androidx.paging.PagingData
import com.labs.network.shared.NetworkResponse
import com.labs.network.shared.createPager
import com.labs.review.api.ReviewService
import com.labs.review.impl.ReviewRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val service: ReviewService
) : ReviewRepository {

    override suspend fun getReview(movieId: String): Flow<PagingData<com.labs.review.impl.mapper.Review>> {
        return createPager { page ->
            val response = service.getReviews(movieId, page)
            val results = response.results.map { result -> result.toReview() }

            NetworkResponse(
                page = response.page,
                totalPages = response.totalPages,
                results = results,
            )
        }.flow.flowOn(Dispatchers.IO)
    }
}