package com.arj.search.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arj.uikit.shimmerLoadingUI

@Composable
internal fun LoadingState() {
    Column {
        Spacer(
            modifier = Modifier
                .padding(start = 4.dp, top = 8.dp, end = 4.dp)
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(CornerSize(percent = 5)))
                .background(color = Color.LightGray)
                .background(shimmerLoadingUI())
        )

        Spacer(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .padding(horizontal = 4.dp)
                .fillMaxWidth()
                .height(24.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.LightGray)
                .background(shimmerLoadingUI())
        )

        Spacer(
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, bottom = 12.dp)
                .fillMaxWidth()
                .height(24.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.LightGray)
                .background(shimmerLoadingUI())
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemLoadingPreview() {
    LoadingState()
}
