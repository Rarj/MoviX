package com.labs.search.impl

import androidx.navigation.NavHostController
import com.labs.search.controller.Navigation
import com.labs.search.controller.SEARCH_ROUTE
import javax.inject.Inject


class NavigationImpl @Inject constructor() : Navigation{

    override fun navigateToSearchPage(navHostController: NavHostController) {
        navHostController.navigate(
            route = SEARCH_ROUTE
        )
    }
}