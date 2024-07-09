package com.labs.home

data class HomeState(
    val isLoading: Boolean? = null,
    val selectedGenre: String? = null,
    val errorMessage: String? = null,
)
