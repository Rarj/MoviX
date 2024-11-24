package com.arj.detail.ui.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.arj.uikit.shimmerLoadingUI

@Composable
internal fun CasterLoading() {
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        items(3) { CasterLoadingItem() }
    }
}

@Composable
private fun CasterLoadingItem(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp, start = 8.dp, end = 8.dp)
    ) {
        val (image, name, department) = createRefs()

        Box(modifier = Modifier
            .constrainAs(image) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
            .height(72.dp)
            .width(72.dp)
            .clip(CircleShape)
            .background(brush = shimmerLoadingUI()))

        Box(
            modifier = Modifier
                .constrainAs(name) {
                    top.linkTo(image.top)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .fillMaxWidth()
                .padding(start = 8.dp, top = 16.dp)
                .height(24.dp)
                .clip(RoundedCornerShape(50))
                .background(brush = shimmerLoadingUI()),
        )

        Box(
            modifier = Modifier
                .constrainAs(department) {
                    top.linkTo(name.bottom)
                    start.linkTo(image.end)
                }
                .padding(top = 4.dp, start = 8.dp)
                .width(72.dp)
                .height(16.dp)
                .clip(RoundedCornerShape(50))
                .background(brush = shimmerLoadingUI()),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CasterLoadingUIPreview() {
    CasterLoadingItem()
}

@Preview(showBackground = true)
@Composable
private fun CasterLoadingPreview() {
    CasterLoading()
}
