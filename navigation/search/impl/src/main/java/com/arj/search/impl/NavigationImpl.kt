package com.arj.search.impl

import androidx.navigation.NavHostController
import com.arj.search.controller.Navigation
import com.arj.search.controller.SEARCH_ROUTE
import javax.inject.Inject


class NavigationImpl @Inject constructor() : Navigation{

    override fun navigateToSearchPage(navHostController: NavHostController) {
        navHostController.navigate(
            route = SEARCH_ROUTE
        )
    }
}