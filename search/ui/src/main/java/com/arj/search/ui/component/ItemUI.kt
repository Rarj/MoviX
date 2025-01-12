package com.arj.search.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arj.search.domain.model.MovieModel
import com.arj.uikit.PosterUiKit
import com.arj.uikit.R

@Composable
internal fun ItemUI(
    movie: MovieModel?,
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
            fontFamily = FontFamily(Font(resId = R.font.sono_semibold)),
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
            fontFamily = FontFamily(Font(resId = R.font.sono_medium)),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemUIPreview() {
    ItemUI(
        movie = MovieModel(
            id = 1,
            posterPath = "poster_path",
            title = "title",
            releaseDate = "release_date",
            overview = "overview",
            genreIds = emptyList(),
            rating = 8.2,
        ),
        onItemClicked = { },
    )
}
