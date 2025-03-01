package com.arj.genre.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arj.uikit.shimmerLoadingUI

@Composable
internal fun FilterLoadingUI() {
    LazyColumn {
        items(7) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .fillMaxWidth()
                    .padding(all = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .width(200.dp)
                        .clip(RoundedCornerShape(50))
                        .background(brush = shimmerLoadingUI())
                )

                Box(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(brush = shimmerLoadingUI())
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FilterLoadingUIPreview() {
    FilterLoadingUI()
}
