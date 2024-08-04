package com.labs.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.labs.data.BuildConfig
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
                        title = response.data?.title.orEmpty(),
                        posterUrl = getPosterUrl(response.data?.posterPath),
                        rating = getRating(response.data?.rating),
                        overview = response.data?.overview.orEmpty(),
                    )
                }
            }
        }
    }

    private fun getRating(rating: Double? = 0.0) = buildString {
        append(rating?.times(10.0)?.roundToInt()?.div(10.0) ?: "N/A")
        append("/10")
    }

    private fun getPosterUrl(path: String?) = buildString {
        append(BuildConfig.IMAGE_BASE_URL)
        append(path)
    }.ifEmpty { "" }

}