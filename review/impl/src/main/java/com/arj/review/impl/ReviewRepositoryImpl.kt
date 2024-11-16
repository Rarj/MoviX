package com.arj.review.impl

import androidx.paging.PagingData
import com.arj.network.shared.MovixNetworkResult
import com.arj.network.shared.NetworkResponse
import com.arj.network.shared.createPager
import com.arj.review.api.ReviewService
import com.arj.review.domain.ReviewRepository
import com.arj.review.domain.model.ReviewModel
import com.arj.review.domain.toReview
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val service: ReviewService,
    private val dispatcher: CoroutineDispatcher,
) : ReviewRepository {

    override suspend fun getReview(movieId: String): Flow<PagingData<ReviewModel>> {
        return createPager { page ->
            val response = service.getReviews(movieId, page)
            val results = response.results.map { result -> result.toReview() }

            val result = NetworkResponse(
                page = response.page,
                totalPages = response.totalPages,
                results = results,
            )
            MovixNetworkResult.Success(result)
        }.flow.flowOn(dispatcher)
    }
}
