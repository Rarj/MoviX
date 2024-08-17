package com.labs.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.labs.detail.impl.DetailMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val repo: DetailMovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailMovieState())
    val state get() = _state.asStateFlow()

    fun getDetailMovie(movieId: String) {
        viewModelScope.launch {
            repo.getDetailMovie(movieId).collectLatest { response ->
                _state.update {
                    it.copy(
                        title = response.title,
                        posterUrl = getBackdropUrl(response.posterPath),
                        rating = getRating(response.rating),
                        overview = response.overview,
                    )
                }
            }
        }
    }

    private fun getRating(rating: Double? = 0.0) = buildString {
        append(rating?.times(10.0)?.roundToInt()?.div(10.0) ?: "N/A")
        append("/10")
    }

}