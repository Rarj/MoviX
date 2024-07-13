package com.labs.home.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.labs.uikit.ToolbarUiKit
import com.labs.uikit.appearance.ColorSecondaryVariant
import com.labs.uikit.R as RUiKit

@Composable
fun HomeUI(
    modifier: Modifier = Modifier,
    selectedGenre: String? = null,
    onSearchClicked: () -> Unit,
    onFilterClicked: () -> Unit,
    contentItem: LazyGridScope.() -> Unit,
) {
    ConstraintLayout(modifier = modifier) {
        val (toolbar, genre, movies) = createRefs()

        ToolbarUiKit(
            modifier = Modifier.constrainAs(toolbar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            onSearchClicked = { onSearchClicked.invoke() },
            onFilterClicked = { onFilterClicked.invoke() },
        )

        Text(
            modifier = Modifier
                .constrainAs(genre) {
                    top.linkTo(toolbar.bottom)
                    start.linkTo(parent.start)
                }
                .padding(top = 16.dp, end = 16.dp, start = 16.dp),
            text = selectedGenre.orEmpty(),
            color = ColorSecondaryVariant,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(RUiKit.font.sono_medium)),
        )

        MoviesUI(
            modifier = Modifier.constrainAs(movies) {
                top.linkTo(genre.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            contentItem = contentItem
        )
    }
}

@Composable
private fun MoviesUI(
    modifier: Modifier,
    contentItem: LazyGridScope.() -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        modifier = modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp),
        content = contentItem
    )
}
