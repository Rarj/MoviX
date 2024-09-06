package com.arj.detail.ui.tab.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.arj.detail.ui.DetailMovieState
import com.arj.uikit.BackdropUiKit
import com.arj.uikit.R
import com.arj.uikit.appearance.ColorStar
import com.arj.uikit.R as RUiKit

@Composable
internal fun OverviewUI(
    modifier: Modifier = Modifier,
    state: DetailMovieState,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val (poster, releaseStatus, genre, synopsys) = createRefs()
        createVerticalChain(
            poster,
            releaseStatus,
            genre,
            synopsys,
            chainStyle = ChainStyle.Packed(0f)
        )

        BackdropUiKit(
            modifier = Modifier.constrainAs(poster) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                height = Dimension.wrapContent
                width = Dimension.fillToConstraints
            },
            path = state.posterPath,
            contentDescription = state.title,
        )

        ReleaseDateComponent(
            modifier = Modifier
                .constrainAs(releaseStatus) {
                    top.linkTo(poster.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.wrapContent
                    width = Dimension.fillToConstraints
                }
                .padding(bottom = 8.dp),
            releaseDate = state.releaseDate,
            status = state.status,
            rating = state.rating,
        )

        GenreComponent(
            modifier = Modifier
                .constrainAs(genre) {
                    top.linkTo(releaseStatus.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.wrapContent
                    width = Dimension.fillToConstraints
                }
                .padding(horizontal = 16.dp, vertical = 8.dp),
            genres = state.genres,
        )

        Text(
            modifier = Modifier
                .constrainAs(synopsys) {
                    top.linkTo(genre.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.wrapContent
                    width = Dimension.fillToConstraints
                }
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 32.dp)
                .semantics {
                    contentDescription = "Synopsys"
                },
            text = state.overview,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.sono_light)),
        )
    }
}

@Composable
private fun GenreComponent(
    modifier: Modifier = Modifier,
    genres: List<String>,
) {
    Row(
        modifier = modifier
            .wrapContentSize()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Center
    ) {
        genres.forEach { genre ->
            Text(
                modifier = Modifier
                    .padding(horizontal = 1.dp)
                    .clip(RoundedCornerShape(50))
                    .background(color = MaterialTheme.colorScheme.tertiary)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                text = genre,
                color = MaterialTheme.colorScheme.onTertiary,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(RUiKit.font.sono_medium)),
            )
        }
    }
}

@Composable
fun ReleaseDateComponent(
    modifier: Modifier = Modifier,
    releaseDate: String,
    status: String,
    rating: String,
) {
    Row(
        modifier = modifier
            .wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = status,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(resId = R.font.sono_bold)),
        )
        VerticalDivider(
            modifier = Modifier
                .height(18.dp)
                .padding(horizontal = 8.dp),
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = releaseDate,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(resId = R.font.sono_bold)),
        )
        VerticalDivider(
            modifier = Modifier
                .height(18.dp)
                .padding(horizontal = 8.dp),
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.secondary
        )
        Icon(
            tint = ColorStar,
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_star),
            contentDescription = "Rating Icon - Star",
        )
        Text(
            modifier = Modifier.padding(start = 2.dp),
            text = rating,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(resId = R.font.sono_bold)),
        )
    }
}

@Preview(name = "Release Date Component", showBackground = true, showSystemUi = true)
@Composable
private fun ReleaseDatePreview() {
    ReleaseDateComponent(
        releaseDate = "2024",
        status = "Canceled",
        rating = "8.9/10",
    )
}

@Preview(name = "Genre Component", showBackground = true, showSystemUi = true)
@Composable
private fun GenreComponentPreview() {
    GenreComponent(
        genres = listOf("Action", "Adventure", "Fantasy")
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OverviewUIPreview() {
    OverviewUI(
        state = DetailMovieState(
            title = "Avenger",
            posterPath = "url",
            rating = "8.9/10",
            overview = "Long Overview",
            genres = listOf("Action", "Adventure", "Fantasy"),
            releaseDate = "2023",
            status = "Released",
        )
    )
}
