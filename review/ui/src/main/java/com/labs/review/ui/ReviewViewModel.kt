package com.labs.review.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.labs.review.impl.ReviewRepository
import com.labs.review.impl.mapper.Review
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository
) : ViewModel() {

    private var _reviewPagingDataState: Flow<PagingData<Review>> =
        flow { PagingData.empty<Review>() }
    val reviewPagingDataState get() = _reviewPagingDataState

    suspend fun getReviews(movieId: String) {
        viewModelScope.launch {
            _reviewPagingDataState = reviewRepository.getReview(movieId = movieId)
        }
    }

}