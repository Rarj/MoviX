package com.labs.search.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout

@Composable

fun SearchUI(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
    ) {
        Text("Search Page")
    }
}