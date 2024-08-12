package com.labs.review.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.labs.review.impl.mapper.Review
import kotlinx.coroutines.flow.flow

@Composable
fun ReviewScreen(
    movieId: String,
    onDismiss: () -> Unit,
    viewModel: ReviewViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = movieId.isNotEmpty()) {
        viewModel.getReviews(movieId)
    }

    ReviewUI(
        reviews = viewModel.reviewPagingDataState.collectAsLazyPagingItems(),
        onDismiss = onDismiss,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReviewUI(
    reviews: LazyPagingItems<Review>,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        LazyColumn(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp),
        ) {
            items(reviews.itemCount) { index ->
                Text(text = reviews[index]?.results?.get(index)?.author.orEmpty())
            }
        }
    }
}

@Preview
@Composable
private fun ReviewUIPreview() {
    ReviewUI(
        reviews = flow<PagingData<Review>> { }.collectAsLazyPagingItems(),
        onDismiss = {},
    )
}
