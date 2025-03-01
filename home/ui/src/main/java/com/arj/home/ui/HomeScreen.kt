package com.arj.home.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.arj.home.domain.mapper.DiscoverMovie
import kotlinx.coroutines.flow.flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    movies: LazyPagingItems<DiscoverMovie>,
    onNavigateToDetailScreen: (movieId: String, movieTitle: String) -> Unit,
    onSearchClicked: () -> Unit,
    onFilterClicked: () -> Unit,
    onAboutClicked: () -> Unit,
) {
    MoviesUI(
        innerPadding = PaddingValues(0.dp),
        pagingItems = movies,
        onItemClicked = onNavigateToDetailScreen::invoke,
    )
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