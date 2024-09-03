package com.arj.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.arj.detail.ui.tab.TabUI
import com.arj.review.ui.ReviewScreen
import com.arj.uikit.BackdropUiKit
import com.arj.uikit.R
import com.arj.uikit.appearance.ColorStar
import com.arj.uikit.R as RUiKit

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
            .background(color = MaterialTheme.colorScheme.primaryContainer)
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
                .padding(top = 56.dp, end = 8.dp),
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
            BackdropUiKit(
                path = state.posterPath,
                contentDescription = state.title,
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
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(resId = R.font.sono_bold))
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .semantics {
                        contentDescription = "Synopsys"
                    },
                text = state.overview,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
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
                contentDescription = "Back to Home page",
            )
        }

        Text(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp)
                .fillMaxWidth()
                .semantics {
                    contentDescription = "Movie Title"
                },
            textAlign = TextAlign.Start,
            maxLines = 3,
            text = title,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 28.sp,
            fontFamily = FontFamily(Font(resId = R.font.sono_extrabold))
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetailMovieUIPreview() {
    DetailMovieUI(
        state = DetailMovieState(
            title = "Avenger",
            posterPath = "url",
            rating = "8.9/10",
            overview = "Long OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong Overview"
        ),
        onBack = {},
        onReview = {},
    )
}
