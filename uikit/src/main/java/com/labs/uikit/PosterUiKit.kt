package com.labs.uikit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun PosterUiKit(
    modifier: Modifier = Modifier,
    path: String?,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    val url = if (path.isNullOrEmpty()) path else buildString {
        append(BuildConfig.IMAGE_BASE_URL)
        append(path)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 4.dp, top = 8.dp, end = 4.dp)
            .height(300.dp)
            .clip(RoundedCornerShape(CornerSize(percent = 5)))
            .background(color = Color.LightGray)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ImageUiKit(url = url, contentDescription = contentDescription)
    }
}

@Composable
fun BackdropUiKit(
    path: String?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    val url = if (path.isNullOrEmpty()) path else buildString {
        append(BuildConfig.BACKDROP_IMAGE_BASE_URL)
        append(path)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
            .height(250.dp)
            .clip(RoundedCornerShape(CornerSize(percent = 5)))
            .background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ImageUiKit(url = url, contentDescription = contentDescription)
    }
}

@Composable
private fun ImageUiKit(
    url: String?,
    contentDescription: String?,
) {
    if (url.isNullOrEmpty()) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_poster_empty),
            contentDescription = "Couldn't Load Image - $contentDescription",
        )
    } else {

        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Poster Movie - $contentDescription"
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
private fun PosterPreview() {
    PosterUiKit(path = "") {}
}