package com.labs.navigation.detail.impl

import androidx.navigation.NavHostController
import com.labs.navigation.detail.controller.DETAIL_MOVIE_ROUTE
import com.labs.navigation.detail.controller.Navigation

class NavigationImpl: Navigation {

    override fun navigateToDetailMoviePage(navHostController: NavHostController) {
        navHostController.navigate(
            route = DETAIL_MOVIE_ROUTE
        )
    }

}