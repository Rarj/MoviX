package com.arj.detail.ui.tab.caster

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arj.detail.domain.mapper.Cast
import com.arj.detail.ui.CreditsMovieUIState
import com.arj.detail.ui.tab.CasterLoading
import com.arj.uikit.BuildConfig
import com.arj.uikit.GenericErrorUI
import com.arj.uikit.R

@Composable
internal fun CasterScreen(
    state: CreditsMovieUIState,
    onRetry: () -> Unit,
) {
    when (state) {
        is CreditsMovieUIState.Init -> {}
        is CreditsMovieUIState.Loading -> CasterLoading()
        is CreditsMovieUIState.Success -> CasterUI(casts = state.data.casts)
        is CreditsMovieUIState.Error -> GenericErrorUI(state.message) { onRetry.invoke() }
    }
}

@Composable
private fun CasterUI(casts: List<Cast>) {
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        items(casts.size) { index ->
            val cast = casts[index]

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp, start = 8.dp, end = 8.dp)
            ) {
                val (image, name, department) = createRefs()

                AsyncImage(modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
                    .height(72.dp)
                    .width(72.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.surfaceVariant),
                    placeholder = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_person)),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(cast.profilePath?.let { getCasterImageUrl(it) }).crossfade(true)
                        .build(),
                    contentScale = ContentScale.Inside,
                    contentDescription = "Caster - ${cast.name}"
                )

                Text(
                    modifier = Modifier
                        .constrainAs(name) {
                            top.linkTo(image.top)
                            start.linkTo(image.end)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(start = 8.dp, top = 16.dp),
                    text = "${cast.name} (${cast.gender})",
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontFamily = FontFamily(Font(resId = R.font.sono_medium)),
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    modifier = Modifier
                        .constrainAs(department) {
                            top.linkTo(name.bottom)
                            start.linkTo(image.end)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                        .padding(start = 8.dp),
                    text = cast.department ?: "Unknown",
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.secondary,
                    fontFamily = FontFamily(Font(resId = R.font.sono_light)),
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis,
                )
            }
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
    CasterUI(
        casts = listOf(
            Cast(
                id = 1,
                name = "Tom Holland Tom Holland Tom Holland",
                profilePath = "path",
                gender = "He",
                department = "Acting"
            ), Cast(
                id = 1,
                name = "Tom Holland Tom Holland Tom Holland",
                profilePath = "path",
                gender = "He",
                department = "Acting"
            )
        )
    )
}
