package com.labs.home.ui.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.labs.home.impl.genre.mapper.Genre
import com.labs.uikit.R
import com.labs.uikit.appearance.ColorGray
import com.labs.uikit.appearance.ColorPrimary
import com.labs.uikit.appearance.ColorWhite

@Composable
fun FilterScreen(
    selectedGenre: String,
    viewModel: GenreViewModel = hiltViewModel(),
    onDismiss: () -> Unit,
    onGenreClicked: (Genre?) -> Unit
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = selectedGenre.isNotEmpty()) {
        viewModel.getGenres()
    }

    FilterUI(
        selectedGenre = selectedGenre,
        genres = state.value.genres,
        onDismiss = onDismiss,
        onGenreClicked = onGenreClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterUI(
    selectedGenre: String,
    genres: List<Genre>?,
    onDismiss: () -> Unit,
    onGenreClicked: (Genre?) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss.invoke() },
        sheetState = sheetState,
        containerColor = ColorPrimary,
    ) {
        genres?.let {
            LazyColumn {
                items(it.size) { index ->
                    val item = it[index]
                    GenreItem(
                        selectedGenre = selectedGenre,
                        genre = item,
                        onGenreClicked = onGenreClicked,
                    )
                }
            }
        }
    }
}

@Composable
private fun GenreItem(
    selectedGenre: String,
    genre: Genre?,
    onGenreClicked: (Genre?) -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxWidth()
            .background(color = ColorPrimary)
            .padding(all = 16.dp)
    ) {
        val (name, divider) = createRefs()
        val color = ColorWhite.takeIf { selectedGenre == genre?.id.toString() } ?: ColorGray

        Text(
            modifier = Modifier
                .constrainAs(name) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .clickable { onGenreClicked.invoke(genre) },
            text = genre?.name.orEmpty(),
            color = color,
            fontFamily = FontFamily(Font(resId = R.font.sono_medium)),
            fontSize = 16.sp,
        )

        HorizontalDivider(
            modifier = Modifier
                .constrainAs(divider) {
                    top.linkTo(name.bottom)
                }
                .padding(top = 6.dp),
            thickness = 1.dp,
            color = color,
        )
    }

}

@Preview
@Composable
private fun GenrePreview() {
    GenreItem(
        selectedGenre = "1",
        genre = Genre(id = 1, name = "Action"),
        {}
    )
}