package com.labs.navigation.home.controller

import androidx.navigation.NavHostController

const val DETAIL_MOVIE_ROUTE = "detail-movie"

interface Navigation {

    fun navigateToDetailScreen(
        movieId: String,
        navHostController: NavHostController,
    )

}