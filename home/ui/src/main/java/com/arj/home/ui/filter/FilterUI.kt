package com.arj.home.ui.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.arj.genre.domain.model.GenreItemModel
import com.arj.uikit.GenericErrorUI
import com.arj.uikit.R
import com.arj.uikit.appearance.ColorSecondary
import kotlinx.coroutines.launch

@Composable
fun FilterScreen(
    selectedGenre: String,
    viewModel: GenreViewModel = hiltViewModel(),
    onDismiss: () -> Unit,
    onGenreClicked: (GenreItemModel?) -> Unit,
) {
    val coroutinesScope = rememberCoroutineScope()
    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = selectedGenre.isNotEmpty()) {
        viewModel.getGenres()
    }

    FilterUI(
        selectedGenre = selectedGenre,
        state = state.value,
        onDismiss = onDismiss,
        onGenreClicked = onGenreClicked,
        onRetry = { coroutinesScope.launch { viewModel.getGenres() } },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterUI(
    state: GenreUIState,
    selectedGenre: String,
    onDismiss: () -> Unit,
    onGenreClicked: (GenreItemModel?) -> Unit,
    onRetry: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss.invoke() },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) {
        when (state) {
            is GenreUIState.Init -> Unit
            is GenreUIState.Loading -> FilterLoadingUI()
            is GenreUIState.Success -> GenreScreen(
                selectedGenre = selectedGenre,
                genres = state.state.genres,
                onGenreClicked = onGenreClicked::invoke,
            )

            is GenreUIState.Error -> GenericErrorUI(
                message = state.message,
                onRetry = onRetry::invoke,
            )
        }
    }
}

@Preview
@Composable
private fun FilterUIPreview() {
    FilterUI(
        state = GenreUIState.Loading,
        selectedGenre = "",
        onDismiss = {},
        onGenreClicked = {},
        onRetry = {},
    )
}

@Composable
fun GenreScreen(
    selectedGenre: String,
    genres: List<GenreItemModel>,
    onGenreClicked: (GenreItemModel?) -> Unit,
) {
    LazyColumn {
        items(genres.size) { index ->
            val item = genres[index]
            GenreItem(
                selectedGenre = selectedGenre,
                genre = item,
                onGenreClicked = onGenreClicked,
            )
        }
    }
}

@Preview
@Composable
private fun GenreScreenPreview() {
    GenreScreen(
        selectedGenre = "2",
        genres = listOf(
            GenreItemModel(
                id = 28,
                name = "Action",
            ),
            GenreItemModel(
                id = 2,
                name = "Action",
            ),
            GenreItemModel(
                id = 28,
                name = "Action",
            ),
            GenreItemModel(
                id = 28,
                name = "Action",
            ),
            GenreItemModel(
                id = 28,
                name = "Action",
            ),
        ),
        onGenreClicked = {},
    )
}

@Composable
private fun GenreItem(
    selectedGenre: String,
    genre: GenreItemModel?,
    onGenreClicked: (GenreItemModel?) -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(all = 16.dp)
    ) {
        val (name, divider, selectedIcon) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(name) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(selectedIcon.start)
                    width = Dimension.fillToConstraints
                }
                .clickable { onGenreClicked.invoke(genre) },
            text = genre?.name.orEmpty(),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontFamily = FontFamily(Font(resId = R.font.sono_medium)),
            fontSize = 16.sp,
        )

        if (selectedGenre == genre?.id.toString()) {
            Icon(
                modifier = Modifier.constrainAs(selectedIcon) {
                    top.linkTo(name.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(name.bottom)

                },
                imageVector = Icons.Default.Check,
                contentDescription = "Selected genre is ${genre?.name}",
                tint = ColorSecondary,
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .constrainAs(divider) {
                    top.linkTo(name.bottom)
                }
                .padding(top = 6.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    }

}

@Preview
@Composable
private fun GenrePreview() {
    GenreItem(selectedGenre = "1", genre = GenreItemModel(id = 1, name = "Action"), {})
}