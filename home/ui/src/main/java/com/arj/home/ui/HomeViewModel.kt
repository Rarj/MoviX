package com.arj.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arj.home.impl.discover.DiscoverMovieRepository
import com.arj.home.impl.genre.GenreRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val genreRepo: GenreRepo,
    private val movieRepository: DiscoverMovieRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state get() = _state.asStateFlow()

    init {
        getGenres()
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

    private fun getGenres() {
        viewModelScope.launch {
            genreRepo.getGenres()
                .catch {  }
                .collectLatest { genres ->
                val genre = genres.firstOrNull()
                if (genre?.id.toString() != state.value.selectedGenreId) {
                    setSelectedGenre(
                        id = genre?.id,
                        name = genre?.name,
                    )
                }
                async { getMovies() }.await()
            }
        }
    }

    fun getMovies() {
        viewModelScope.launch {
            val response = movieRepository.getDiscoverMovie(state.value.selectedGenreId)
                .cachedIn(this)
                .catch {  }

            _state.update {
                it.copy(
                    moviePagingDataState = response,
                )
            }
        }
    }

}