package com.labs.home.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.labs.data.BuildConfig
import com.labs.home.impl.discover.mapper.DiscoverMovie
import com.labs.uikit.PosterUiKit
import com.labs.uikit.ToolbarUiKit
import com.labs.uikit.appearance.ColorPrimary
import com.labs.uikit.appearance.ColorSecondaryVariant
import com.labs.uikit.R as RUiKit

@Composable
fun HomeUI(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onSearchClicked: () -> Unit,
    onFilterClicked: () -> Unit,
    onItemClicked: (movieId: String) -> Unit,
) {
    val context = LocalContext.current
    val selectedGenre = context.getString(
        R.string.selected_genre_label,
        viewModel.state.collectAsState().value.selectedGenre.orEmpty()
    )

    ConstraintLayout(
        modifier = modifier
            .background(color = ColorPrimary)
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
            onFilterClicked = { onFilterClicked.invoke() },
        )

        Text(
            modifier = Modifier
                .constrainAs(genre) {
                    top.linkTo(toolbar.bottom)
                    start.linkTo(parent.start)
                }
                .padding(top = 16.dp, end = 16.dp, start = 16.dp),
            text = selectedGenre,
            color = ColorSecondaryVariant,
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
            pagingItems = viewModel.moviePagingDataState.collectAsLazyPagingItems(),
        ) { movieId ->
            onItemClicked.invoke(movieId)
        }
    }
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
            PosterUiKit(url = buildString {
                append(BuildConfig.IMAGE_BASE_URL)
                append(pagingItems[index]?.posterPath)
            }) {
                onItemClicked.invoke(pagingItems[index]?.id.toString())
            }
        }
    }
}
