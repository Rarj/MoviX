package com.labs.movix.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.labs.data.Status
import com.labs.data.ViewState
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

    private val _genres = MutableStateFlow<ViewState<List<Genre>>?>(null)
    val genre get() = _genres.asStateFlow()

    var movieFlow: Flow<PagingData<Movie>> = flow { PagingData.empty<Movie>() }
        private set

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
                _genres.value = when (genres.status) {
                    Status.LOADING -> ViewState.loading()
                    Status.SUCCESS -> {
                        setSelectedGenre(genres.data?.firstOrNull())
                        async { getMovies() }.await()
                        ViewState.success(genres.data.orEmpty())
                    }
                    Status.ERROR -> ViewState.error(genres.message.toString())
                }
            }
        }
    }

    private suspend fun getMovies() {
        viewModelScope.launch {
            movieFlow = movieRepository.getDiscoverMovie()
        }
    }

}