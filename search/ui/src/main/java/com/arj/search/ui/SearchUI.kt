package com.arj.search.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
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
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 24.dp)
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        val (topBar, movies) = createRefs()
        createVerticalChain(topBar, movies)

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
            pagingItems = state.moviePagingItems.collectAsLazyPagingItems(),
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
