package com.arj.detail.ui.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arj.detail.ui.DetailMovieState
import com.arj.detail.ui.tab.caster.CastUI
import com.arj.detail.ui.tab.overview.OverviewUI

@Composable
internal fun TabUI(
    modifier: Modifier,
    state: DetailMovieState,
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val tabItems = listOf(
        DetailMovieTabItem(
            name = "Overview",
            selectedIndex = 0
        ) { OverviewUI( state = state) },
        DetailMovieTabItem(
            name = "Casters",
            selectedIndex = 1,
        ) { CastUI(state = state) },
        DetailMovieTabItem(
            name = "Crews",
            selectedIndex = 2,
        ) { CastUI(state = state) }
    )

    Column(
        modifier = modifier,
    ) {
        TabRow(
            selectedTabIndex = selectedIndex,
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 16.dp)
                .clip(RoundedCornerShape(percent = 25))
                .background(MaterialTheme.colorScheme.onPrimaryContainer)
                .padding(1.dp),
            indicator = { Box { } },
            divider = { },
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(RoundedCornerShape(percent = 25))
                        .background(
                            if (selectedIndex == index) MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    text = {
                        Text(text = item.name)
                    }
                )
            }
        }

        tabItems[selectedIndex].screen()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TabPreview() {
    TabUI(
        modifier = Modifier, state = DetailMovieState(
            title = "Avenger",
            posterPath = "url",
            rating = "8.9/10",
        )
    )
}
