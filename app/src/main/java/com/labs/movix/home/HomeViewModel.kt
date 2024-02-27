package com.labs.movix.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.labs.data.Status
import com.labs.data.ViewState
import com.labs.data.repository.genre.Genre
import com.labs.data.repository.genre.GenreRepository
import com.labs.data.repository.movie.Movie
import com.labs.data.repository.movie.MovieRepository
import com.labs.data.repository.movie.Review
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val genreRepository: GenreRepository,
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _genres = MutableStateFlow<ViewState<List<Genre>>?>(null)
    val genre get() = _genres.asStateFlow()

    private val _movies =  MutableStateFlow<PagingData<Movie>?>(null)
    val movies get() = _movies.asStateFlow()

    private lateinit var selectedGenre: Genre

    fun setSelectedGenre(genre: Genre?) {
        genre?.let {
            selectedGenre = it
        }
    }

    fun getSelectedGenre() = selectedGenre

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

    suspend fun getMovie(genre: Genre? = null) {
        viewModelScope.launch {
            genreRepository.getGenres().collectLatest { genres ->
                _genres.value = when (genres.status) {
                    Status.LOADING -> ViewState.loading()
                    Status.SUCCESS -> {
                        genre?.let {
                            setSelectedGenre(it)
                        } ?: setSelectedGenre(genres.data?.firstOrNull())
                        async {
                            discoverMovie(listOf(selectedGenre.id.toString()))
                        }.await()

                        ViewState.success(genres.data.orEmpty())
                    }
                    Status.ERROR -> ViewState.error(genres.message.toString())
                }
            }
        }
    }

    private suspend fun discoverMovie(genreIds: List<String>) {
        viewModelScope.launch {
            movieRepository.getDiscoverMovie(genreIds)
                .cachedIn(this)
                .collectLatest { movies ->
                _movies.emit(movies)
            }
        }
    }

}