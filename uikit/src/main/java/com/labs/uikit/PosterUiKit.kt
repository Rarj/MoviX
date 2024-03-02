package com.labs.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun PosterUiKit(url: String, onClick: () -> Unit) {
    Column {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 8.dp)
                .height(260.dp)
                .clip(RoundedCornerShape(CornerSize(percent = 5)))
                .background(color = Color.LightGray)
                .clickable {
                    onClick()
                },
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Poster Movie"
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
private fun PosterPreview() {
    PosterUiKit(url = "https://img.freepik.com/free-photo/eiffel-tower-with-bridge-river-seine-paris-france_649448-4968.jpg") {}
}