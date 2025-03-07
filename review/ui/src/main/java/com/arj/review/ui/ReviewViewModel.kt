package com.arj.review.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arj.review.domain.usecase.ReviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val reviewUseCase: ReviewUseCase,
) : ViewModel() {

    private var _state = MutableStateFlow(ReviewState())
    val state get() = _state.asStateFlow()

    suspend fun getReviews(movieId: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    reviewPagingItem = reviewUseCase.invoke(movieId = movieId).cachedIn(this)
                )
            }
        }
    }

}