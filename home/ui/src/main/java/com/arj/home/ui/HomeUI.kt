package com.arj.home.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.arj.home.impl.discover.mapper.DiscoverMovie
import com.arj.home.ui.filter.FilterScreen
import com.arj.uikit.PosterUiKit
import com.arj.uikit.ToolbarUiKit
import com.arj.uikit.R as RUiKit

@Composable
fun HomeUI(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onSearchClicked: () -> Unit,
    onItemClicked: (movieId: String) -> Unit,
) {
    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current
    val selectedGenre = context.getString(
        R.string.selected_genre_label,
        state.selectedGenre.orEmpty()
    )
    val isAboutClicked = remember { mutableStateOf(false) }

    if (isAboutClicked.value) AlertAboutUI(isAboutClicked)
    val filterPageState = remember { mutableStateOf(false) }

    if (filterPageState.value) {
        FilterScreen(
            selectedGenre = state.selectedGenreId.orEmpty(),
            onDismiss = { filterPageState.value = !filterPageState.value },
            onGenreClicked = { genre ->
                filterPageState.value = !filterPageState.value

                viewModel.apply {
                    setSelectedGenre(genre?.id, genre?.name)
                    getMovies()
                }
            }
        )
    }

    ConstraintLayout(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(top = 24.dp)
    ) {
        val (toolbar, genre, movies) = createRefs()

        ToolbarUiKit(
            modifier = Modifier.constrainAs(toolbar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            onSearchClicked = { onSearchClicked.invoke() },
            onFilterClicked = { filterPageState.value = !filterPageState.value },
            onAboutClicked = { isAboutClicked.value = !isAboutClicked.value }
        )

        Text(
            modifier = Modifier
                .constrainAs(genre) {
                    top.linkTo(toolbar.bottom)
                    start.linkTo(parent.start)
                }
                .padding(top = 16.dp, end = 16.dp, start = 16.dp),
            text = selectedGenre,
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(RUiKit.font.sono_medium)),
        )

        MoviesUI(
            modifier = Modifier
                .constrainAs(movies) {
                    top.linkTo(genre.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .animateContentSize(),
            pagingItems = state.moviePagingDataState.collectAsLazyPagingItems(),
        ) { movieId ->
            onItemClicked.invoke(movieId)
        }
    }
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
private fun MoviesUI(
    modifier: Modifier,
    pagingItems: LazyPagingItems<DiscoverMovie>,
    onItemClicked: (movieId: String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        modifier = modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp),
    ) {
        items(pagingItems.itemCount) { index ->
            Item(discoverMovie = pagingItems[index]) {
                onItemClicked.invoke(pagingItems[index]?.id.toString())
            }
        }
    }
}

@Composable
private fun Item(
    discoverMovie: DiscoverMovie?,
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
                .padding(start = 4.dp, end = 4.dp, bottom = 12.dp)
                .semantics {
                    contentDescription = discoverMovie?.title.orEmpty()
                },
            text = discoverMovie?.releaseDate.orEmpty(),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            maxLines = 1,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(resId = RUiKit.font.sono_medium)),
        )
    }
}

@Preview
@Composable
private fun HomePreview() {
    Item(
        discoverMovie = DiscoverMovie(
            id = 1,
            posterPath = "poster_path",
            genreIds = emptyList(),
            title = "Garfield",
            overview = "overview",
            rating = 10.0,
            releaseDate = "17 August 2024"
        )
    ) { }
}
