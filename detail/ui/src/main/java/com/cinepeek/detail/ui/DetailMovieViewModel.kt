package com.cinepeek.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinepeek.detail.domain.mapper.CreditsMovie
import com.cinepeek.detail.domain.mapper.DetailMovie
import com.cinepeek.detail.domain.usecase.CreditUseCase
import com.cinepeek.detail.domain.usecase.DetailMovieUseCase
import com.cinepeek.network.state.CinepeekNetworkResult
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

    private val _movieState = MutableStateFlow<DetailMovieUIState>(DetailMovieUIState.Init)
    val movieState get() = _movieState.asStateFlow()

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

    private fun creditsMovieResponseHandler(response: CinepeekNetworkResult<CreditsMovie>) {
        val uiState = when (response) {
            is CinepeekNetworkResult.Loading -> CreditsMovieUIState.Loading
            is CinepeekNetworkResult.Success -> CreditsMovieUIState.Success(
                CreditsMovieState(
                    casts = response.value.casts.orEmpty(),
                    crews = response.value.crews.orEmpty(),
                )
            )

            is CinepeekNetworkResult.Failed -> CreditsMovieUIState.Error(response.message)
        }
        _creditsState.value = uiState
    }

    private fun detailMovieResponseHandler(response: CinepeekNetworkResult<DetailMovie>) {
        val uiState = when (response) {
            is CinepeekNetworkResult.Loading -> DetailMovieUIState.Loading
            is CinepeekNetworkResult.Success -> DetailMovieUIState.Success(
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

            is CinepeekNetworkResult.Failed -> DetailMovieUIState.Error(response.message)
        }
        setDetailMovieUiState(uiState)
    }

    private fun setDetailMovieUiState(state: DetailMovieUIState) {
        _movieState.value = state
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
