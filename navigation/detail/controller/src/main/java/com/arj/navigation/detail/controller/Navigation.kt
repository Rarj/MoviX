package com.arj.navigation.detail.controller

import androidx.navigation.NavHostController

const val DETAIL_MOVIE_ROUTE = "detail_movie_route/{movieId}/{movieTitle}"
const val DETAIL_MOVIE_ID_ARGS = "movieId"
const val DETAIL_MOVIE_TITLE_ARGS = "movieTitle"

interface Navigation {

    fun navigateToDetailMoviePage(
        navHostController: NavHostController,
        movieId: String,
        movieTitle: String,
    )

}