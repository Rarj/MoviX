package com.arj.movix.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector
import com.arj.navigation.home.controller.HOME_ROUTE
import com.arj.search.controller.SEARCH_ROUTE

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String,
    val isHideBottomNav: Boolean = false
) {
    object Home : BottomNavItem(HOME_ROUTE, Icons.Default.Home, "Home")
    object NowPlaying : BottomNavItem(SEARCH_ROUTE, Icons.Rounded.PlayArrow, "Now Playing")
}