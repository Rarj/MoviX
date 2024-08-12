package com.labs.review.ui

import androidx.paging.PagingData
import com.labs.review.impl.mapper.ReviewItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class ReviewState(
    val reviewPagingItem: Flow<PagingData<ReviewItem>> = flow { PagingData.empty<ReviewItem>() },
)
