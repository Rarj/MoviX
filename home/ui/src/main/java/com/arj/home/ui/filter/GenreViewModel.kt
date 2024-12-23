package com.arj.home.ui.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arj.genre.domain.GenreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val genreRepo: GenreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(GenreState())
    val state get() = _state.asStateFlow()

    suspend fun getGenres() {
        viewModelScope.launch {
            val response = genreRepo.getGenres()
            response.collectLatest { genres ->
                _state.update {
                    it.copy(
                        genres = genres.genres
                    )
                }
            }
        }
    }

}