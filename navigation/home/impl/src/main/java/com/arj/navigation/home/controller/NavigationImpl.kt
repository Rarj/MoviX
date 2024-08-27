package com.arj.navigation.home.controller

import androidx.navigation.NavHostController
import javax.inject.Inject

class NavigationImpl @Inject constructor() : Navigation {

    override fun navigateToDetailScreen(
        movieId: String,
        navHostController: NavHostController,
    ) {
        navHostController.navigate(
            route = DETAIL_MOVIE_ROUTE,
        )
    }
}