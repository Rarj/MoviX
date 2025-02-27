package com.arj.home.ui.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arj.home.domain.usecase.HomeNowPlayingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    private val nowPlayingUseCase: HomeNowPlayingUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(NowPlayingState())
    val state get() = _state.asStateFlow()

    init {
        getNowPlayingMovies()
    }

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    nowPlayingMoviePagingData = nowPlayingUseCase.invoke().cachedIn(this)
                )
            }
        }
    }

}