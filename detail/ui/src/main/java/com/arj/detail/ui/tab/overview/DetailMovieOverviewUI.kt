package com.arj.detail.ui.tab.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.arj.detail.ui.DetailMovieState
import com.arj.uikit.BackdropUiKit
import com.arj.uikit.R
import com.arj.uikit.appearance.ColorStar

@Composable
internal fun OverviewUI(
    modifier: Modifier = Modifier,
    state: DetailMovieState,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val (poster, rating, synopsys) = createRefs()
        createVerticalChain(
            poster,
            rating,
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

        Row(
            modifier = Modifier
                .constrainAs(rating) {
                    top.linkTo(poster.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.wrapContent
                    width = Dimension.fillToConstraints
                }
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                tint = ColorStar,
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_star),
                contentDescription = "Rating Icon - Star",
            )

            Text(
                text = state.rating,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(resId = R.font.sono_bold)),
            )
        }

        Text(
            modifier = Modifier
                .constrainAs(synopsys) {
                    top.linkTo(rating.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.wrapContent
                    width = Dimension.fillToConstraints
                }
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
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