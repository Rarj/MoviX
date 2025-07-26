package com.cinepeek.navigation.detail.impl

import androidx.navigation.NavHostController
import com.cinepeek.navigation.detail.controller.Navigation
import javax.inject.Inject

class NavigationImpl @Inject constructor() : Navigation {

    override fun navigateToDetailMoviePage(
        navHostController: NavHostController,
        movieId: String,
        movieTitle: String,
    ) {
        navHostController.navigate(
            route = "detail_movie_route/${movieId}/${movieTitle}"
        )
    }

}