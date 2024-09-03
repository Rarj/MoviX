package com.arj.detail.ui.tab.caster

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arj.detail.impl.mapper.Cast
import com.arj.detail.ui.DetailMovieState
import com.arj.uikit.BuildConfig
import com.arj.uikit.R as RUiKit

@Composable
internal fun CastUI(
    state: DetailMovieState,
) {
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(state.casts.size) { index ->
            val cast = state.casts[index]

            AsyncImage(
                modifier = Modifier
                    .padding(4.dp)
                    .height(96.dp)
                    .width(96.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.surfaceVariant),
                placeholder = rememberVectorPainter(image = ImageVector.vectorResource(id = RUiKit.drawable.ic_person)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(cast.profilePath?.let { getCasterImageUrl(it) })
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.FillBounds,
                contentDescription = "Caster - ${cast.name}"
            )
        }
    }
}

private fun getCasterImageUrl(path: String): String {
    return buildString {
        append(BuildConfig.CAST_IMAGE_BASE_URL)
        append(path)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CastUIPreview() {
    CastUI(
        state = DetailMovieState(
            casts = listOf(
                Cast(
                    id = 1,
                    name = "Tom Holland",
                    profilePath = "path",
                    gender = "He",
                )
            )
        )
    )
}
