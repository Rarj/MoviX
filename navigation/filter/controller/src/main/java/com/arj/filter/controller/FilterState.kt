package com.arj.filter.controller

sealed interface FilterState {
    object VISIBLE : FilterState
    object HIDE : FilterState
}