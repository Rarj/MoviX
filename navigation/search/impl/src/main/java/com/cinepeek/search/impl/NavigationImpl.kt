package com.cinepeek.search.impl

import androidx.navigation.NavHostController
import com.cinepeek.search.controller.Navigation
import com.cinepeek.search.controller.SEARCH_ROUTE
import javax.inject.Inject


class NavigationImpl @Inject constructor() : Navigation{

    override fun navigateToSearchPage(navHostController: NavHostController) {
        navHostController.navigate(
            route = SEARCH_ROUTE
        )
    }
}