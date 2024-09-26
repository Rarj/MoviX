package com.arj.search.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.arj.search.impl.mapper.Movie
import com.arj.uikit.PosterUiKit
import com.arj.uikit.R as RUiKit

@Composable
fun SearchUI(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onItemClicked: (movieId: String) -> Unit,
) {
    val state = viewModel.state.collectAsState().value

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 24.dp)
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        val (topBar, movies) = createRefs()
        createVerticalChain(topBar, movies)

        Row(
            modifier = Modifier
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                }
                .wrapContentSize()
                .padding(top = 62.dp, end = 16.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically),
                onClick = { onBack.invoke() }
            ) {
                Icon(
                    painter = painterResource(id = RUiKit.drawable.ic_back),
                    contentDescription = "Back to Home page",
                )
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterVertically),
                value = state.keyword.toString(),
                onValueChange = { text ->
                    viewModel.onUpdateKeyword(keyword = text)
                },
                placeholder = {
                    Text(
                        fontSize = 14.sp,
                        text = "Search Movie...",
                        color = MaterialTheme.colorScheme.secondary,
                        fontFamily = FontFamily(Font(resId = RUiKit.font.sono_regular)),
                    )
                },
                trailingIcon = {
                    ClearTextIcon(state.keyword.toString()) {
                        viewModel.onUpdateKeyword(keyword = "")
                    }
                },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontFamily = FontFamily(Font(resId = RUiKit.font.sono_regular))
                ),
                shape = RoundedCornerShape(percent = 50),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Words,
                ),
            )
        }

        MoviesUI(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(movies) {
                    top.linkTo(topBar.bottom)
                    height = Dimension.fillToConstraints
                }
                .animateContentSize(),
            pagingItems = state.moviePagingItems.collectAsLazyPagingItems(),
        ) { movieId ->
            onItemClicked.invoke(movieId)
        }
    }
}

@Composable
private fun MoviesUI(
    modifier: Modifier,
    pagingItems: LazyPagingItems<Movie>,
    onItemClicked: (movieId: String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        modifier = modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp),
    ) {
        items(pagingItems.itemCount) { index ->
            Item(movie = pagingItems[index]) {
                onItemClicked.invoke(pagingItems[index]?.id.toString())
            }
        }
    }
}


@Composable
private fun ClearTextIcon(
    keyword: String,
    onClearedText: () -> Unit
) {
    if (keyword.isBlank().not()) {
        IconButton(onClick = { onClearedText.invoke() }) {
            Icon(
                painter = painterResource(id = RUiKit.drawable.ic_close),
                contentDescription = "Clear Keyword",
            )
        }
    }
}

@Composable
private fun Item(
    movie: Movie?,
    onItemClicked: (movieId: String) -> Unit,
) {
    Column {
        PosterUiKit(
            path = movie?.posterPath,
            contentDescription = movie?.title,
        ) { onItemClicked.invoke(movie?.id.toString()) }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp)
                .semantics {
                    contentDescription = movie?.title.orEmpty()
                },
            text = movie?.title.orEmpty(),
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
                    contentDescription = movie?.title.orEmpty()
                },
            text = movie?.releaseDate.orEmpty(),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            maxLines = 1,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(resId = RUiKit.font.sono_medium)),
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SearchUIPreview() {
    SearchUI(
        onBack = { },
        onItemClicked = { }
    )
}
