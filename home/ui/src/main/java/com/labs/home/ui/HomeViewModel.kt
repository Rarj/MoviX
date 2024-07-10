package com.labs.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.labs.data.Status
import com.labs.data.repository.genre.Genre
import com.labs.data.repository.genre.GenreRepository
import com.labs.data.repository.movie.Movie
import com.labs.data.repository.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val genreRepository: GenreRepository,
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state get() = _state.asStateFlow()

    private var _moviePagingDataState: Flow<PagingData<Movie>> = flow { PagingData.empty<Movie>() }
    val moviePagingDataState get() = _moviePagingDataState

    fun setSelectedGenre(genre: Genre?) {
        movieRepository.setSelectedGenre(genre)
        _state.update {
            it.copy(
                selectedGenre = genre?.name
            )
        }
    }

    fun getSelectedGenre() = movieRepository.getSelectedGenre()

    fun getGenres() {
        viewModelScope.launch {
            genreRepository.getGenres().collectLatest { genres ->
                when (genres.status) {
                    Status.LOADING -> {
                        _state.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    Status.SUCCESS -> {
                        setSelectedGenre(genres.data?.firstOrNull())
                        async { getMovies() }.await()
                    }

                    Status.ERROR -> {
                        _state.update {
                            it.copy(
                                errorMessage = genres.message
                            )
                        }
                    }
                }
            }
        }
    }

    private suspend fun getMovies() {
        viewModelScope.launch {
            _moviePagingDataState = movieRepository.getDiscoverMovie()
        }
    }

}