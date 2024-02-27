package com.labs.movix.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.labs.data.repository.genre.Genre
import com.labs.data.repository.movie.Movie
import com.labs.data.repository.movie.search.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
) : ViewModel() {

    private var _movie = MutableStateFlow<PagingData<Movie>?>(null)
    val movie get() = _movie.asStateFlow()

    private lateinit var selectedGenre: Genre

    fun selectedGenre(id: Int) {
        selectedGenre = Genre(id = id, name = "")
    }

    suspend fun searchMovie(keyword: String) {
        viewModelScope.launch {
            searchRepository.searchMovie(
                keyword = keyword,
                selectedGenre = selectedGenre,
            ).cachedIn(this).collectLatest { state ->
                val movies = state.filter { movie ->
                    movie.genreIds.any { id ->
                        id == selectedGenre.id.toString()
                    }
                }
                _movie.emit(movies)
            }
        }
    }

}