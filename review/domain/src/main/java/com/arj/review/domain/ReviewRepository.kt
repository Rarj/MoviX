package com.arj.review.domain

import androidx.paging.PagingData
import com.arj.review.domain.model.ReviewModel
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {

    suspend fun getReview(movieId: String): Flow<PagingData<ReviewModel>>

}
