package com.labs.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.labs.review.ui.ReviewScreen
import com.labs.uikit.R
import com.labs.uikit.appearance.ColorPrimary
import com.labs.uikit.appearance.ColorSecondaryVariant
import com.labs.uikit.appearance.ColorStar
import com.labs.uikit.appearance.ColorWhite
import com.labs.uikit.R as RUiKit

@Composable
fun DetailMovieScreen(
    viewModel: DetailMovieViewModel = hiltViewModel(),
    movieId: String,
    onBack: () -> Unit,
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(movieId.isNotEmpty()) {
        viewModel.getDetailMovie(movieId)
    }

    DetailMovieUI(
        state = viewModel.state.collectAsState().value,
        onBack = { onBack.invoke() },
        onReview = { showBottomSheet = !showBottomSheet })

    if (showBottomSheet) {
        ReviewScreen(
            movieId = movieId,
            onDismiss = { showBottomSheet = false },
        )
    }
}

@Composable
private fun DetailMovieUI(
    state: DetailMovieState,
    onBack: () -> Unit,
    onReview: () -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ColorPrimary)
    ) {
        val (topBar, content, buttonSeeReview) = createRefs()
        createVerticalChain(
            topBar,
            content,
            buttonSeeReview,
        )

        ToolbarUI(
            Modifier
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                }
                .fillMaxWidth()
                .wrapContentSize()
                .padding(top = 32.dp, end = 8.dp),
            title = state.title,
            onBack = onBack,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(content) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                }
                .padding(top = 8.dp)
                .verticalScroll(state = rememberScrollState())
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(CornerSize(percent = 5)))
                    .background(color = Color.LightGray),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(state.posterUrl)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillBounds,
                contentDescription = "Poster Movie"
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier
                        .wrapContentSize(),
                    tint = ColorStar,
                    imageVector = ImageVector.vectorResource(id = RUiKit.drawable.ic_star),
                    contentDescription = "Rating Icon - Star"
                )

                Text(
                    modifier = Modifier
                        .wrapContentSize(),
                    text = state.rating,
                    maxLines = 1,
                    color = ColorWhite,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(resId = R.font.sono_bold))
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                text = state.overview,
                color = ColorWhite,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.sono_light))
            )
        }

        Button(
            modifier = Modifier
                .constrainAs(buttonSeeReview) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
            onClick = { onReview.invoke() },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorSecondaryVariant
            )
        ) {
            Text(text = "See Review")
        }
    }
}

@Composable
private fun ToolbarUI(
    modifier: Modifier,
    title: String,
    onBack: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        IconButton(
            onClick = { onBack.invoke() }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = RUiKit.drawable.ic_back),
                tint = ColorWhite,
                contentDescription = null,
            )
        }

        Text(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Start,
            maxLines = 3,
            text = title,
            color = ColorWhite,
            fontSize = 28.sp,
            fontFamily = FontFamily(Font(resId = R.font.sono_extrabold))
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFF2C394B)
@Composable
private fun DetailMovieUIPreview() {
    DetailMovieUI(
        state = DetailMovieState(
            title = "Avenger",
            posterUrl = "url",
            rating = "8.9/10",
            overview = "Long OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong Overview"
        ),
        onBack = {},
        onReview = {},
    )
}
