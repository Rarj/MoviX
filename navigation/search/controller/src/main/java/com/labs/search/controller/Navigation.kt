package com.labs.search.controller

import androidx.navigation.NavHostController

const val SEARCH_ROUTE = "search_route"

interface Navigation {

    fun navigateToSearchPage(navHostController: NavHostController)

}