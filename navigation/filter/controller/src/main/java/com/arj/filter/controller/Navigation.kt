package com.arj.filter.controller

import kotlinx.coroutines.flow.StateFlow

interface Navigation {
    val state: StateFlow<FilterState>
    fun showBottomSheet()
    fun hideBottomSheet()
}