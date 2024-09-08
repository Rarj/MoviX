package com.arj.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arj.detail.impl.DetailMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
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
            val detailMovieFlow = repo.getDetailMovie(movieId)
            val creditsFlow = repo.getCredits(movieId)

            async {
                detailMovieFlow.collectLatest { response ->
                    _state.update {
                        it.copy(
                            title = response.title,
                            posterPath = response.posterPath,
                            rating = getRating(response.rating),
                            overview = response.overview,
                            releaseDate = response.releaseDate,
                            status = response.status,
                            genres = response.genres.orEmpty().map { genre -> genre.name },
                        )
                    }
                }
                creditsFlow.collectLatest { response ->
                    _state.update {
                        it.copy(
                            casts = response.casts.orEmpty(),
                            crews = response.crews.orEmpty(),
                        )
                    }
                }
            }.await()
        }
    }

    private fun getRating(rating: Double? = 0.0) = buildString {
        if (rating != 0.0) {
            append(rating?.times(10.0)?.roundToInt()?.div(10.0))
            append("/10")
        } else {
            append("No Rating")
        }
    }

}