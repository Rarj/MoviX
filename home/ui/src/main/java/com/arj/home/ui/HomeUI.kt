package com.arj.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.arj.home.ui.filter.FilterScreen
import com.arj.uikit.PosterUiKit
import com.arj.uikit.R as RUiKit

@Composable
fun HomeUI(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onSearchClicked: () -> Unit,
    onItemClicked: (movieId: String, movieTitle: String) -> Unit,
) {
    val state = viewModel.state.collectAsState().value
    val isAboutClicked = remember { mutableStateOf(false) }

    if (isAboutClicked.value) AlertAboutUI(isAboutClicked)
    val filterPageState = remember { mutableStateOf(false) }

    if (filterPageState.value) {
        FilterScreen(selectedGenre = state.selectedGenreId.orEmpty(),
            onDismiss = { filterPageState.value = !filterPageState.value },
            onGenreClicked = { genre ->
                filterPageState.value = !filterPageState.value

                viewModel.apply {
                    setSelectedGenre(genre?.id, genre?.name)
                    getMovies()
                }
            })
    }

    HomeScreen(
        modifier = modifier,
        movies = state.moviePagingDataState.collectAsLazyPagingItems(),
        onNavigateToDetailScreen = onItemClicked::invoke,
        onSearchClicked = onSearchClicked::invoke,
        onFilterClicked = { filterPageState.value = !filterPageState.value },
        onAboutClicked = { isAboutClicked.value = !isAboutClicked.value },
    )
}

@Composable
private fun AlertAboutUI(isAboutClicked: MutableState<Boolean>) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        title = {
            Text(
                modifier = Modifier.clickable { isAboutClicked.value = !isAboutClicked.value },
                text = "This product uses the TMDB API but is not endorsed or certified by TMDB.",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(resId = RUiKit.font.sono_medium)),
            )
        },
        onDismissRequest = { isAboutClicked.value = !isAboutClicked.value },
        confirmButton = {
            Text(
                modifier = Modifier
                    .clickable { isAboutClicked.value = !isAboutClicked.value }
                    .padding(all = 8.dp),
                text = "Got It",
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(resId = RUiKit.font.sono_medium)),
            )
        },
    )
}

@Composable
internal fun MoviesUI(
    modifier: Modifier,
    pagingItems: LazyPagingItems<com.arj.home.domain.mapper.DiscoverMovie>,
    onItemClicked: (movieId: String, movieTitle: String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        items(pagingItems.itemCount) { index ->
            Item(
                discoverMovie = pagingItems[index],
                onItemClicked = {
                    onItemClicked.invoke(
                        pagingItems[index]?.id.toString(),
                        pagingItems[index]?.title.toString(),
                    )
                },
            )
        }

        when (pagingItems.loadState.append) {
            is LoadState.NotLoading -> Unit
            is LoadState.Loading -> {
                item {
                    ItemLoading()
                }
            }

            is LoadState.Error -> {
                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    ItemErrorUI(
                        onRetry = { pagingItems.retry() },
                    )
                }
            }
        }
    }
}

@Composable
private fun Item(
    discoverMovie: com.arj.home.domain.mapper.DiscoverMovie?,
    onItemClicked: (movieId: String) -> Unit,
) {
    Column {
        PosterUiKit(
            path = discoverMovie?.posterPath,
            contentDescription = discoverMovie?.title,
        ) { onItemClicked.invoke(discoverMovie?.id.toString()) }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp)
                .semantics {
                    contentDescription = discoverMovie?.title.orEmpty()
                },
            text = discoverMovie?.title.orEmpty(),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            maxLines = 1,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(resId = RUiKit.font.sono_semibold)),
            overflow = TextOverflow.Ellipsis
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp, bottom = 2.dp)
                .semantics {
                    contentDescription = discoverMovie?.title.orEmpty()
                },
            text = discoverMovie?.releaseDate.orEmpty(),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            maxLines = 1,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(resId = RUiKit.font.sono_medium)),
        )

        discoverMovie?.let {
            Text(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .padding(horizontal = 4.dp)
                    .clip(RoundedCornerShape(50))
                    .background(color = Color(it.releaseStatusBackground))
                    .padding(horizontal = 8.dp, vertical = 2.dp),
                text = it.releaseStatus.uppercase(),
                color = MaterialTheme.colorScheme.onTertiary,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(RUiKit.font.sono_medium)),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    Item(
        discoverMovie = com.arj.home.domain.mapper.DiscoverMovie(
            id = 1,
            posterPath = "poster_path",
            genreIds = emptyList(),
            title = "Garfield",
            overview = "overview",
            rating = 10.0,
            releaseDate = "17 August 2024",
            releaseStatus = "Released",
            releaseStatusBackground = 0xFF009688,
        ),
        onItemClicked = { },
    )
}
