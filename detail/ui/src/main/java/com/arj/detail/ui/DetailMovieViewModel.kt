package com.arj.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arj.detail.domain.mapper.CreditsMovie
import com.arj.detail.domain.mapper.DetailMovie
import com.arj.detail.domain.usecase.CreditUseCase
import com.arj.detail.domain.usecase.DetailMovieUseCase
import com.arj.network.state.MovixNetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val detailUseCase: DetailMovieUseCase,
    private val creditsCreditUseCase: CreditUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<DetailMovieUIState>(DetailMovieUIState.Init)
    val state get() = _state.asStateFlow()

    private val _creditsState = MutableStateFlow<CreditsMovieUIState>(CreditsMovieUIState.Init)
    val creditsState get() = _creditsState.asStateFlow()

    fun getDetailMovie(movieId: String) {
        viewModelScope.launch {
            val detailMovieFlow = detailUseCase.invoke(movieId)
            val creditsFlow = creditsCreditUseCase.invoke(movieId)

            async {
                detailMovieFlow.collectLatest { response ->
                    detailMovieResponseHandler(response)
                }
                creditsFlow.collectLatest { response ->
                    creditsMovieResponseHandler(response)
                }
            }.await()
        }
    }

    private fun creditsMovieResponseHandler(response: MovixNetworkResult<CreditsMovie>) {
        val uiState = when (response) {
            is MovixNetworkResult.Loading -> CreditsMovieUIState.Loading
            is MovixNetworkResult.Success -> CreditsMovieUIState.Success(
                CreditsMovieState(
                    casts = response.value.casts.orEmpty(),
                    crews = response.value.crews.orEmpty(),
                )
            )
            is MovixNetworkResult.Failed -> CreditsMovieUIState.Error(response.message)
        }
        _creditsState.value = uiState
    }

    private fun detailMovieResponseHandler(response: MovixNetworkResult<DetailMovie>) {
        val uiState = when (response) {
            is MovixNetworkResult.Loading -> DetailMovieUIState.Loading
            is MovixNetworkResult.Success -> DetailMovieUIState.Success(
                DetailMovieState(
                    title = response.value.title,
                    posterPath = response.value.posterPath,
                    rating = getRating(response.value.rating),
                    overview = response.value.overview,
                    releaseDate = response.value.releaseDate,
                    status = response.value.status,
                    genres = response.value.genres.orEmpty().map { genre -> genre.name },
                )
            )

            is MovixNetworkResult.Failed -> DetailMovieUIState.Error(response.message)
        }
        setDetailMovieUiState(uiState)
    }

    private fun setDetailMovieUiState(state: DetailMovieUIState) {
        _state.value = state
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
