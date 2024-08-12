package com.labs.review.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.labs.review.impl.mapper.ReviewItem
import com.labs.uikit.appearance.ColorGray
import com.labs.uikit.appearance.ColorPrimary
import com.labs.uikit.appearance.ColorStar
import com.labs.uikit.appearance.ColorWhite
import kotlinx.coroutines.flow.flow
import com.labs.uikit.R as RUiKit

@Composable
fun ReviewScreen(
    movieId: String,
    onDismiss: () -> Unit,
    viewModel: ReviewViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = movieId.isNotEmpty()) {
        viewModel.getReviews(movieId)
    }

    val state = viewModel.state.collectAsState().value.reviewPagingItem
    ReviewUI(
        reviews = state.collectAsLazyPagingItems(),
        onDismiss = onDismiss,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReviewUI(
    reviews: LazyPagingItems<ReviewItem>,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        containerColor = ColorPrimary,
    ) {
        reviews.apply {
            when  {
                 loadState.refresh is LoadState.NotLoading -> {
                     if (reviews.itemCount <= 0) ReviewEmptyUI()

                     LazyColumn {
                         items(reviews.itemCount) { index ->
                             val item = reviews[index]
                             ReviewItemUI(
                                 review = ReviewItem(
                                     id = item?.id.orEmpty(),
                                     author = item?.author.orEmpty(),
                                     content = item?.content.orEmpty(),
                                 )
                             )
                         }
                     }
                 }
            }
        }
    }
}

@Composable
private fun ReviewItemUI(
    review: ReviewItem
) {
    ConstraintLayout(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth()
            .background(color = ColorPrimary)
            .padding(all = 16.dp)
    ) {
        val (author, content, divider) = createRefs()

        Text(
            modifier = Modifier.constrainAs(author) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            text = review.author,
            color = ColorGray,
            maxLines = 1,
            fontFamily = FontFamily(Font(resId = RUiKit.font.sono_medium)),
            fontSize = 16.sp
        )

        Text(
            modifier = Modifier
                .constrainAs(content) {
                    top.linkTo(author.bottom)
                    start.linkTo(parent.start)
                }
                .padding(top = 4.dp),
            text = review.content,
            color = ColorWhite,
            fontFamily = FontFamily(Font(resId = RUiKit.font.sono_medium)),
            fontSize = 14.sp
        )

        HorizontalDivider(
            modifier = Modifier
                .constrainAs(divider) {
                    top.linkTo(content.bottom)
                }
                .padding(top = 6.dp),
            thickness = 2.dp,
            color = ColorStar,
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ReviewUIPreview() {
    ReviewUI(
        reviews = flow<PagingData<ReviewItem>> {
            PagingData.from(
                listOf(
                    ReviewItem(
                        id = "1",
                        author = "Rio Arj",
                        content = "This is a good movie!",
                    ),
                    ReviewItem(
                        id = "1",
                        author = "Rio Arj",
                        content = "This is a good movie!",
                    ),
                    ReviewItem(
                        id = "1",
                        author = "Rio Arj",
                        content = "This is a good movie!",
                    ),
                    ReviewItem(
                        id = "1",
                        author = "Rio Arj",
                        content = "This is a good movie!",
                    )
                )
            )
        }.collectAsLazyPagingItems(),
        onDismiss = {},
    )
}
