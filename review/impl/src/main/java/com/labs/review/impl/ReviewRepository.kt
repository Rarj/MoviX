package com.labs.review.impl

import androidx.paging.PagingData
import com.labs.review.impl.mapper.ReviewItem
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {

    suspend fun getReview(movieId: String): Flow<PagingData<ReviewItem>>

}