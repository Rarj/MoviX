package com.arj.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.arj.uikit.ToolbarShimmerLoading
import com.arj.uikit.shimmerLoadingUI

@Composable
internal fun LoadingUI(
    onBackEvent: () -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val (topBar, tab, poster, releaseStatus, genre, synopsys, review) = createRefs()
        createVerticalChain(
            topBar,
            tab,
            poster,
            releaseStatus,
            genre,
            synopsys,
            review,
            chainStyle = ChainStyle.Packed(0f)
        )

        ToolbarShimmerLoading(
            modifier = Modifier
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.wrapContent
                    width = Dimension.fillToConstraints
                }
                .padding(top = 56.dp)
        ) { onBackEvent.invoke() }

        Box(
            modifier = Modifier
                .constrainAs(tab) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.wrapContent
                    width = Dimension.fillToConstraints
                }
                .padding(top = 8.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
                .height(50.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(brush = shimmerLoadingUI()),
        ) {}

        Column(
            modifier = Modifier
                .constrainAs(poster) {
                    top.linkTo(tab.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.wrapContent
                    width = Dimension.fillToConstraints
                }
                .fillMaxWidth()
                .padding(all = 16.dp)
                .height(250.dp)
                .clip(RoundedCornerShape(CornerSize(percent = 5)))
                .background(color = Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {}

        Row(
            modifier = Modifier
                .constrainAs(releaseStatus) {
                    top.linkTo(poster.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.wrapContent
                    width = Dimension.fillToConstraints
                }
                .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(3) {
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(16.dp)
                        .padding(horizontal = 1.dp)
                        .clip(RoundedCornerShape(50))
                        .background(brush = shimmerLoadingUI())
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                )
            }
        }

        Row(
            modifier = Modifier
                .constrainAs(genre) {
                    top.linkTo(releaseStatus.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.wrapContent
                    width = Dimension.fillToConstraints
                }
                .wrapContentSize()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(3) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 2.dp, vertical = 16.dp)
                        .width(80.dp)
                        .height(36.dp)
                        .clip(RoundedCornerShape(50))
                        .background(brush = shimmerLoadingUI())
                )
            }
        }

        Column(
            modifier = Modifier
                .constrainAs(synopsys) {
                    top.linkTo(genre.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.wrapContent
                    width = Dimension.fillToConstraints
                }
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 32.dp),
        ) {
            repeat(5) {
                val modifier = Modifier
                if (it % 2 == 0) modifier.width(100.dp) else modifier.fillMaxWidth()
                Box(
                    modifier = modifier
                        .clip(RoundedCornerShape(16.dp))
                        .padding(horizontal = 2.dp, vertical = 4.dp)
                        .fillMaxWidth()
                        .height(24.dp)
                        .background(brush = shimmerLoadingUI())
                )
            }
        }

        Column(
            modifier = Modifier
                .constrainAs(review) {
                    top.linkTo(synopsys.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.wrapContent
                    width = Dimension.fillToConstraints
                }
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(brush = shimmerLoadingUI())
            ) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingUIPreview() {
    LoadingUI(
        onBackEvent = {},
    )
}
