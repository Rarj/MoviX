package com.cinepeek.home.ui.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinepeek.genre.domain.model.GenreModel
import com.cinepeek.genre.domain.usecase.GenreUseCase
import com.cinepeek.network.state.CinepeekNetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val genreUseCase: GenreUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<GenreUIState>(GenreUIState.Init)
    val state get() = _state.asStateFlow()

    suspend fun getGenres() {
        viewModelScope.launch {
            genreUseCase.invoke().collectLatest { response ->
                genreResponseHandler(response)
            }
        }
    }

    private fun genreResponseHandler(response: CinepeekNetworkResult<GenreModel>) {
        val state = when (response) {
            is CinepeekNetworkResult.Loading -> GenreUIState.Loading
            is CinepeekNetworkResult.Success -> GenreUIState.Success(GenreState(response.value.genres))
            is CinepeekNetworkResult.Failed -> GenreUIState.Error(response.message)
        }
        _state.value = state
    }

}