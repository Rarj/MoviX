package com.labs.navigation.detail.impl

import androidx.navigation.NavHostController
import com.labs.navigation.detail.controller.DETAIL_MOVIE_ID_ARGS
import com.labs.navigation.detail.controller.DETAIL_MOVIE_ROUTE
import com.labs.navigation.detail.controller.Navigation
import javax.inject.Inject

class NavigationImpl @Inject constructor() : Navigation {

    override fun navigateToDetailMoviePage(
        navHostController: NavHostController,
        movieId: String,
    ) {
        navHostController.navigate(
            route = "detail_movie_route/${movieId}"
        )
    }

}