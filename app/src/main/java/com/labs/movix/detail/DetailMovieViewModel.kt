package com.labs.movix.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.labs.data.Status
import com.labs.data.ViewState
import com.labs.data.repository.movie.Movie
import com.labs.data.repository.movie.MovieRepository
import com.labs.data.repository.movie.Review
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _movies = MutableStateFlow<ViewState<Movie>?>(null)
    val movies get() = _movies.asStateFlow()

    private val _reviews = MutableStateFlow<PagingData<Review>?>(null)
    val reviews get() = _reviews.asStateFlow()

    suspend fun getDetailMovie(id: String) {
        viewModelScope.launch {
            movieRepository.getDetailMovie(id).collectLatest { movies ->
                _movies.value = when (movies.status) {
                    Status.LOADING -> ViewState.loading()
                    Status.SUCCESS -> ViewState.success(movies.data)
                    Status.ERROR -> ViewState.error(movies.message.toString())
                }
            }
        }
    }

    suspend fun getReviews(movieId: String) {
        viewModelScope.launch {
            movieRepository.getReviews(movieId)
                .cachedIn(this)
                .collectLatest {
                _reviews.value = it
            }
        }
    }

}