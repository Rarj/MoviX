package com.arj.filter.impl.state

import com.arj.filter.controller.FilterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class FilterViewModel @Inject constructor() {
    private val _state = MutableStateFlow<FilterState>(FilterState.HIDE)
    val state get() = _state.asStateFlow()

    internal fun showFilterBottomSheet() {
        _state.value = FilterState.VISIBLE
    }

    internal fun hideFilterBottomSheet() {
        _state.value = FilterState.HIDE
    }
}