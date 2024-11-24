package com.arj.detail.ui.tab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arj.detail.ui.CreditsMovieUIState
import com.arj.detail.ui.DetailMovieState
import com.arj.detail.ui.tab.caster.CastUI
import com.arj.detail.ui.tab.crew.CrewUI
import com.arj.detail.ui.tab.overview.OverviewUI
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TabUI(
    modifier: Modifier,
    movieState: DetailMovieState,
    creditState: CreditsMovieUIState,
    onReview: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val tabItems = listOf(
        DetailMovieTabItem(
            name = "Overview", selectedIndex = 0
        ) {
            OverviewUI(
                state = movieState,
                onReview = onReview,
            )
        },
        DetailMovieTabItem(
            name = "Casters",
            selectedIndex = 1,
        ) {},
        DetailMovieTabItem(
            name = "Crews",
            selectedIndex = 2,
        ) {},
    )
    val pagerState = rememberPagerState { tabItems.size }

    Column(
        modifier = modifier,
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
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
                Tab(modifier = Modifier
                    .wrapContentWidth()
                    .clip(RoundedCornerShape(percent = 25))
                    .background(
                        if (pagerState.currentPage == index) MaterialTheme.colorScheme.primaryContainer
                        else MaterialTheme.colorScheme.onPrimaryContainer
                    ), selected = pagerState.currentPage == index, onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }, text = {
                    Text(text = item.name)
                })
            }
        }

        HorizontalPager(

            state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            tabItems[index].screen()
        }
    }

    when (creditState) {
        is CreditsMovieUIState.Init -> {}
        is CreditsMovieUIState.Loading -> {
            tabItems[1].screen = { CasterLoading() }
            tabItems[2].screen = { CasterLoading() }
        }
        is CreditsMovieUIState.Success -> {
            tabItems[1].screen = { CastUI(casts = creditState.data.casts) }
            tabItems[2].screen = { CrewUI(crews = creditState.data.crews) }
        }
        is CreditsMovieUIState.Error -> {}
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TabPreview() {
    TabUI(
        modifier = Modifier,
        movieState = DetailMovieState(
            title = "Avenger",
            posterPath = "url",
            rating = "8.9/10",
        ),
        creditState = CreditsMovieUIState.Loading,
        onReview = {},
    )
}
