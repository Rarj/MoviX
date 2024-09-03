package com.arj.detail.ui.tab

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.arj.detail.ui.DetailMovieState
import com.arj.detail.ui.tab.caster.CastUI
import com.arj.detail.ui.tab.overview.OverviewUI

@Composable
internal fun TabUI(
    state: DetailMovieState,
) {
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
                modifier = Modifier.wrapContentWidth(),
                selected = selectedIndex == index,
                onClick = { selectedIndex = index },
                text = {
                    Text(text = item.name)
                }
            )
        }
    }

    when (selectedIndex) {
        0 -> OverviewUI(state = state)
        1 -> CastUI(state = state)
        2 -> CrewUI()
    }
}