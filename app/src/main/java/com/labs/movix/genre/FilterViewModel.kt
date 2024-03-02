package com.labs.movix.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.labs.data.Status
import com.labs.data.ViewState
import com.labs.data.repository.genre.Genre
import com.labs.data.repository.genre.GenreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val genreRepository: GenreRepository,
) : ViewModel() {

    private val _genres = MutableStateFlow<ViewState<List<Genre>>?>(null)
    val genre get() = _genres.asStateFlow()

    fun getGenre() {
        viewModelScope.launch {
            genreRepository.getGenres().collectLatest { genres ->
                _genres.value = when (genres.status) {
                    Status.LOADING -> ViewState.loading()
                    Status.SUCCESS -> ViewState.success(genres.data.orEmpty())
                    Status.ERROR -> ViewState.error(genres.message.toString())
                }
            }
        }
    }

}