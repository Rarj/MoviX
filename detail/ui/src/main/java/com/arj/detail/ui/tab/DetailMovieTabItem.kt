package com.arj.detail.ui.tab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
data class DetailMovieTabItem(
    val name: String = "",
    val selectedIndex: Int? = null,
    var screen: @Composable () -> Unit,
)
