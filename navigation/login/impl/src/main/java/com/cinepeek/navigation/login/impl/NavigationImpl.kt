package com.cinepeek.navigation.login.impl

import androidx.navigation.NavHostController
import com.cinepeek.navigation.login.controller.Navigation
import javax.inject.Inject

class NavigationImpl @Inject constructor() : Navigation {

	override fun navigateToLoginPage(navHostController: NavHostController) {
		navHostController.navigate(
			route = "login_route"
		)
	}
}