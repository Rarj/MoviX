package com.labs.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.labs.data.Status
import com.labs.home.impl.discover.DiscoverMovieRepository
import com.labs.home.impl.discover.mapper.DiscoverMovie
import com.labs.home.impl.genre.GenreRepo
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
    private val genreRepo: GenreRepo,
    private val movieRepository: DiscoverMovieRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state get() = _state.asStateFlow()

    private var _moviePagingDataState: Flow<PagingData<DiscoverMovie>> =
        flow { PagingData.empty<DiscoverMovie>() }
    val moviePagingDataState get() = _moviePagingDataState

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

    fun getGenres() {
        viewModelScope.launch {
            genreRepo.getGenres().collectLatest { genres ->
                when (genres.status) {
                    Status.LOADING -> {
                        _state.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    Status.SUCCESS -> {
                        val genre = genres.data?.firstOrNull()
                        if (genre?.id.toString() != state.value.selectedGenreId) {
                            setSelectedGenre(
                                id = genre?.id,
                                name = genre?.name,
                            )
                        }
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

    suspend fun getMovies() {
        viewModelScope.launch {
            _moviePagingDataState = movieRepository.getDiscoverMovie(state.value.selectedGenreId)
        }
    }

}