package com.labs.search.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.labs.data.BuildConfig
import com.labs.search.impl.mapper.Movie
import com.labs.uikit.PosterUiKit
import com.labs.uikit.appearance.ColorGray
import com.labs.uikit.appearance.ColorPrimary
import com.labs.uikit.appearance.ColorWhite
import kotlinx.coroutines.flow.flow
import com.labs.uikit.R as RUiKit

@Composable
fun SearchUI(
    modifier: Modifier = Modifier,
    searchState: SearchState,
    onBack: () -> Unit,
    onUpdateKeyword: (String) -> Unit,
    onItemClicked: (movieId: String) -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(color = ColorPrimary)
    ) {
        val (topBar, movies) = createRefs()

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
                    tint = ColorWhite,
                    contentDescription = null,
                )
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterVertically),
                value = searchState.keyword.toString(),
                onValueChange = {
                    onUpdateKeyword.invoke(it)
                },
                placeholder = {
                    Text(
                        text = "Search Movie...",
                        color = ColorGray,
                        fontFamily = FontFamily(Font(resId = RUiKit.font.sono_regular)),
                    )
                },
                trailingIcon = {
                    ClearTextIcon(searchState.keyword.toString()) {
                        onUpdateKeyword.invoke("")
                    }
                },
                textStyle = TextStyle(
                    color = ColorWhite,
                ),
                shape = RoundedCornerShape(8.dp),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Words,
                ),
            )
        }

        MoviesUI(
            modifier = Modifier
                .constrainAs(movies) {
                    top.linkTo(topBar.bottom)
                }
                .animateContentSize(),
            pagingItems = searchState.moviePagingItems.collectAsLazyPagingItems(),
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
            PosterUiKit(url = buildString {
                append(BuildConfig.IMAGE_BASE_URL)
                append(pagingItems[index]?.posterPath)
            }) {
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
                contentDescription = null,
                tint = ColorWhite,
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SearchUIPreview() {
    SearchUI(
        searchState = SearchState(
            keyword = "Batman",
            moviePagingItems = flow {
                PagingData.from(
                    listOf(
                        Movie(
                            id = 1,
                            posterPath = "/40RZP4mEel441OVcNyFCTFzHi4o.jpg",
                            genreIds = emptyList(),
                            title = "Title",
                            overview = "overview",
                            rating = 1.3
                        )
                    )
                )
            }
        ),
        onBack = { },
        onUpdateKeyword = { },
        onItemClicked = { }
    )
}
