package com.arj.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.arj.detail.ui.tab.TabUI
import com.arj.review.ui.ReviewScreen
import com.arj.uikit.R as RUiKit

@Composable
fun DetailMovieScreen(
    viewModel: DetailMovieViewModel = hiltViewModel(),
    movieId: String,
    onBack: () -> Unit,
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val creditState = viewModel.creditsState.collectAsState().value

    LaunchedEffect(movieId.isNotEmpty()) {
        viewModel.getDetailMovie(movieId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (val movieUIState = viewModel.state.collectAsState().value) {
            is DetailMovieUIState.Init -> Unit
            is DetailMovieUIState.Loading -> LoadingUI()
            is DetailMovieUIState.Success -> {
                DetailMovieUI(
                    movieState = movieUIState.data,
                    creditState = creditState,
                    onBack = { onBack.invoke() },
                    onReview = { showBottomSheet = !showBottomSheet },
                )
            }

            is DetailMovieUIState.Error -> {}
        }
    }

    if (showBottomSheet) {
        ReviewScreen(
            movieId = movieId,
            onDismiss = { showBottomSheet = false },
        )
    }
}

@Composable
private fun DetailMovieUI(
    modifier: Modifier = Modifier,
    movieState: DetailMovieState,
    creditState: CreditsMovieUIState,
    onBack: () -> Unit,
    onReview: () -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        val (topBar, tab) = createRefs()
        createVerticalChain(
            topBar, tab, chainStyle = ChainStyle.Packed(0f)
        )

        ToolbarUI(
            Modifier
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(tab.top)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
                .fillMaxWidth()
                .wrapContentSize()
                .padding(top = 56.dp),
            title = movieState.title,
            onBack = onBack,
        )

        TabUI(modifier = Modifier
            .constrainAs(tab) {
                top.linkTo(topBar.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
            .padding(top = 8.dp),
            movieState = movieState,
            creditState = creditState,
            onReview = onReview)
    }
}

@Composable
private fun ToolbarUI(
    modifier: Modifier,
    title: String,
    onBack: () -> Unit,
) {
    Row(
        modifier = modifier
    ) {
        IconButton(onClick = { onBack.invoke() }) {
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
            text = title,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(resId = RUiKit.font.sono_extrabold))
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DetailMovieUIPreview() {
    DetailMovieUI(
        movieState = DetailMovieState(
            title = "Avenger",
            posterPath = "url",
            rating = "8.9/10",
            overview = "Long OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong OverviewLong Overview"
        ),
        creditState = CreditsMovieUIState.Loading,
        onBack = {},
        onReview = {},
    )
}
