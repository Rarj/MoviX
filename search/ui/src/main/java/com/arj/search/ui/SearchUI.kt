package com.arj.search.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.arj.search.ui.component.EmptyState
import com.arj.search.ui.component.LoadingState
import com.arj.search.ui.component.MoviesUI
import com.arj.search.ui.component.ToolbarUI

@Composable
fun SearchUI(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onItemClicked: (movieId: String, movieTitle: String) -> Unit,
) {
    val state = viewModel.state.collectAsState().value
    SearchPage(
        modifier = modifier,
        state = state,
        onValueChange = viewModel::onUpdateKeyword,
        onBack = onBack::invoke,
        onItemClicked = onItemClicked::invoke,
    )
}

@Composable
private fun SearchPage(
    modifier: Modifier = Modifier,
    state: SearchState,
    onValueChange: (String) -> Unit,
    onBack: () -> Unit,
    onItemClicked: (movieId: String, movieTitle: String) -> Unit,
) {
    val pagingItems = state.moviePagingItems.collectAsLazyPagingItems()

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 24.dp)
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        val (topBar, movies, emptyUI, loadingUI) = createRefs()
        createVerticalChain(topBar, movies)

        if (state.keyword?.isEmpty() == true) {
            EmptyState(
                modifier = Modifier
                    .constrainAs(emptyUI) {
                        top.linkTo(topBar.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                message = "Type to discover good movie",
            )
        } else if (
            pagingItems.itemCount <= 0 &&
            pagingItems.loadState.refresh is LoadState.NotLoading
        ) {
            EmptyState(
                modifier = Modifier
                    .constrainAs(emptyUI) {
                        top.linkTo(topBar.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxSize(),
                message = "Movie not found. \nJust try another keyword.",
            )
        } else if (
            state.keyword?.isNotBlank() == true &&
            pagingItems.loadState.refresh is LoadState.Loading
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 2),
                modifier = modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp)
                    .constrainAs(loadingUI) {
                        top.linkTo(topBar.bottom)

                    },
            ) {
                items(2) { _ ->
                    LoadingState()
                }
            }
        }

        ToolbarUI(
            modifier = Modifier.constrainAs(topBar) {
                top.linkTo(parent.top)
            },
            keyword = state.keyword.toString(),
            onValueChange = onValueChange,
            onBack = onBack::invoke,
        )

        MoviesUI(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(movies) {
                    top.linkTo(topBar.bottom)
                    height = Dimension.fillToConstraints
                }
                .animateContentSize(),
            pagingItems = pagingItems,
            onItemClicked = onItemClicked::invoke,
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SearchUIPreview() {
    SearchPage(
        state = SearchState(
            keyword = "Koala Kumal",
        ),
        onValueChange = { _ -> },
        onBack = { },
        onItemClicked = { _, _ -> },
    )
}
