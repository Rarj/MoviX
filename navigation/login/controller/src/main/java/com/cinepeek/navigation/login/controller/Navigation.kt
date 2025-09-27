package com.cinepeek.navigation.login.controller

import androidx.navigation.NavHostController

const val LOGIN_ROUTE = "login_route"

interface Navigation {

	fun navigateToLoginPage(
		navHostController: NavHostController,
	)

}