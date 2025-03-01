package com.arj.genre.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arj.genre.domain.model.GenreModel
import com.arj.genre.domain.usecase.GenreUseCase
import com.arj.network.state.MovixNetworkResult
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

    private fun genreResponseHandler(response: MovixNetworkResult<GenreModel>) {
        val state = when (response) {
            is MovixNetworkResult.Loading -> GenreUIState.Loading
            is MovixNetworkResult.Success -> GenreUIState.Success(GenreState(response.value.genres))
            is MovixNetworkResult.Failed -> GenreUIState.Error(response.message)
        }
        _state.value = state
    }

}