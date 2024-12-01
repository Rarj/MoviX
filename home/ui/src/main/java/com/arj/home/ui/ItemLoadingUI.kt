package com.arj.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arj.uikit.shimmerLoadingUI

@Composable
internal fun ItemLoading(
    width: Dp = 150.dp,
) {
    Column {
        Spacer(
            modifier = Modifier
                .padding(start = 4.dp, top = 8.dp, end = 4.dp)
                .fillMaxWidth()
//                .width(width)
                .height(300.dp)
                .clip(RoundedCornerShape(CornerSize(percent = 5)))
                .background(shimmerLoadingUI())
        )

        Spacer(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .padding(horizontal = 4.dp)
//                .width(width)
                .height(24.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(shimmerLoadingUI())
        )

        Spacer(
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, bottom = 12.dp)
//                .width(width)
                .height(24.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(shimmerLoadingUI())
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemLoadingPreview() {
    ItemLoading()
}
