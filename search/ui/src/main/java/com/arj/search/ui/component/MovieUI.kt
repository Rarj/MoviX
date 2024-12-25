package com.arj.search.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.arj.search.domain.model.MovieModel
import kotlinx.coroutines.flow.flow

@Composable
internal fun MoviesUI(
    modifier: Modifier,
    pagingItems: LazyPagingItems<MovieModel>,
    onItemClicked: (movieId: String, movieTitle: String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        modifier = modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp),
    ) {
        items(pagingItems.itemCount) { index ->
            ItemUI(movie = pagingItems[index]) {
                onItemClicked.invoke(
                    pagingItems[index]?.id.toString(),
                    pagingItems[index]?.title.toString(),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieUIPreview() {
    val pagingItems = flow<PagingData<MovieModel>> {
        PagingData.from(
            listOf(
                MovieModel(
                    id = 1,
                    posterPath = "poster_path",
                    title = "title",
                    releaseDate = "release_date",
                    overview = "overview",
                    genreIds = emptyList(),
                    rating = 8.2,
                )
            )
        )
    }
    MoviesUI(
        modifier = Modifier,
        pagingItems = pagingItems.collectAsLazyPagingItems(),
        onItemClicked = { id, title -> },
    )
}
