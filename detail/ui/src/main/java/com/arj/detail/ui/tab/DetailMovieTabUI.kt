package com.arj.detail.ui.tab

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun TabUI() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val tabItems = listOf(
        DetailMovieTabItem(
            name = "Overview",
            selectedIndex = 0,
        ),
        DetailMovieTabItem(
            name = "Casters",
            selectedIndex = 1,
        ),
        DetailMovieTabItem(
            name = "Crews",
            selectedIndex = 2,
        )
    )

    TabRow(selectedTabIndex = selectedIndex) {
        tabItems.forEachIndexed { index, item ->
            Tab(
                selected = selectedIndex == index,
                onClick = { selectedIndex = index },
                text = {
                    Text(text = item.name)
                }
            )
        }
    }

    when (selectedIndex) {
        0 -> OverviewUI()
        1 -> DetailMovieUI()
        2 -> CrewUI()
    }
}