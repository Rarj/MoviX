package com.cinepeek.review.impl

import androidx.paging.PagingData
import com.cinepeek.network.shared.NetworkResponse
import com.cinepeek.network.shared.createPager
import com.cinepeek.network.state.MovixNetworkResult
import com.cinepeek.review.api.ReviewService
import com.cinepeek.review.domain.ReviewRepository
import com.cinepeek.review.domain.model.ReviewModel
import com.cinepeek.review.domain.toReview
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
