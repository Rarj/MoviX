package com.arj.detail.ui.tab.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.arj.detail.ui.DetailMovieState
import com.arj.uikit.R
import com.arj.uikit.appearance.ColorStar

@Composable
internal fun OverviewUI(
    state: DetailMovieState,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                modifier = Modifier
                    .wrapContentSize(),
                tint = ColorStar,
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_star),
                contentDescription = "Rating Icon - Star",
            )

            Text(
                modifier = Modifier
                    .wrapContentSize(),
                text = state.rating,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(resId = R.font.sono_bold)),
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
            fontFamily = FontFamily(Font(R.font.sono_light)),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OverviewUIPreview() {
    OverviewUI(
        state = DetailMovieState(
            title = "Avenger",
            posterPath = "url",
            rating = "8.9/10",
            overview = "Long Overview"
        )
    )
}
