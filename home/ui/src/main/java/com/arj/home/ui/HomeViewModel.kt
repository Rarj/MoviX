package com.arj.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arj.home.domain.DiscoverMovieRepository
import com.arj.home.domain.usecase.HomeWithDefaultGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: DiscoverMovieRepository,
    private val homeUseCase: HomeWithDefaultGenreUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state get() = _state.asStateFlow()

    init {
        getMoviesWithDefaultGenre()
    }

    fun setSelectedGenre(
        id: Int?,
        name: String?,
    ) {
        _state.update {
            it.copy(
                selectedGenreId = if (id == 0) "" else id.toString(),
                selectedGenre = name,
            )
        }
    }

    fun getMovies() {
        viewModelScope.launch {
            val response = movieRepository.getDiscoverMovie(state.value.selectedGenreId)
                .cachedIn(this)

            _state.update {
                it.copy(
                    moviePagingDataState = response,
                )
            }
        }
    }

    private fun getMoviesWithDefaultGenre() {
        viewModelScope.launch {
            val response = homeUseCase.invoke().cachedIn(this)
            _state.update {
                it.copy(
                    moviePagingDataState = response,
                )
            }
        }
    }

}