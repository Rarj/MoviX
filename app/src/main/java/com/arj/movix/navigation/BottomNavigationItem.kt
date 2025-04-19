package com.arj.movix.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector
import com.arj.navigation.home.controller.HOME_ROUTE

internal sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String,
) {
    object Home : BottomNavItem(
        route = HOME_ROUTE,
        icon = Icons.Default.Home,
        label = "Home",
    )

    object NowPlaying : BottomNavItem(
        route = HOME_ROUTE,
        icon = Icons.Rounded.PlayArrow,
        label = "Now Playing",
    )
}