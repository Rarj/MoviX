package com.labs.navigation.detail.controller

import androidx.navigation.NavHostController

const val DETAIL_MOVIE_ROUTE = "detail_movie_route"

interface Navigation {

    fun navigateToDetailMoviePage(navHostController: NavHostController)

}