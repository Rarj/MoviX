package com.cinepeek.review.ui

import androidx.paging.PagingData
import com.cinepeek.review.domain.model.ReviewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class ReviewState(
    val reviewPagingItem: Flow<PagingData<ReviewModel>> = flow { PagingData.empty<ReviewModel>() },
)
