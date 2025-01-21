package com.arj.home.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.arj.home.domain.mapper.DiscoverMovie
import com.arj.uikit.ToolbarUiKit
import kotlinx.coroutines.flow.flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    movies: LazyPagingItems<DiscoverMovie>,
    onNavigateToDetailScreen: (movieId: String, movieTitle: String) -> Unit,
    onSearchClicked: () -> Unit,
    onFilterClicked: () -> Unit,
    onAboutClicked: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ToolbarUiKit(
                onSearchClicked = onSearchClicked::invoke,
                onFilterClicked = onFilterClicked::invoke,
                onAboutClicked = onAboutClicked::invoke,
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        MoviesUI(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .animateContentSize(),
            pagingItems = movies,
            onItemClicked = onNavigateToDetailScreen::invoke,
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    val movies = flow<PagingData<DiscoverMovie>> {
        PagingData.empty<DiscoverMovie>()
    }
    HomeScreen(
        movies = movies.collectAsLazyPagingItems(),
        onNavigateToDetailScreen = { _, _ -> },
        onSearchClicked = {},
        onFilterClicked = {},
        onAboutClicked = {},
    )
}