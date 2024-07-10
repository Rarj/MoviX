package com.labs.home.ui

data class HomeState(
    val isLoading: Boolean? = null,
    val selectedGenre: String? = null,
    val errorMessage: String? = null,
)
