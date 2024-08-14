package com.labs.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.labs.search.impl.SearchRepository
import com.labs.search.impl.mapper.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.cache
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state get() = _state.asStateFlow()

    @OptIn(FlowPreview::class)
    private fun searchMovieNew(keyword: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    moviePagingItems = searchRepository.searchMovie(keyword = keyword)
                        .debounce(2000L)
                        .cachedIn(this)
                )
            }
        }
    }

    fun onUpdateKeywordNew(keyword: String) {
        searchMovieNew(
            keyword = _state.updateAndGet {
                it.copy(
                    keyword = keyword
                )
            }.keyword.toString()
        )
    }

}