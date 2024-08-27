package com.arj.uikit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarUiKit(
    modifier: Modifier = Modifier,
    onSearchClicked: () -> Unit,
    onFilterClicked: () -> Unit,
    onAboutClicked: () -> Unit,
) {
    TopAppBar(modifier = modifier
        .shadow(
            elevation = 8.dp,
            spotColor = MaterialTheme.colorScheme.scrim,
        ), colors = TopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        scrolledContainerColor = Color.Unspecified,
        navigationIconContentColor = Color.Unspecified,
        titleContentColor = Color.Unspecified,
        actionIconContentColor = Color.Unspecified,
    ), navigationIcon = {
        OutlinedIconButton(
            modifier = Modifier.padding(start = 4.dp),
            onClick = { onAboutClicked.invoke() },
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.inverseSurface)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_movie),
                contentDescription = null,
            )
        }
    }, title = {
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(
                text = "Welcome Movie Buffs!",
                fontFamily = FontFamily(Font(resId = R.font.sono_bold)),
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
            Text(
                text = "Find your funky movie!",
                fontFamily = FontFamily(Font(resId = R.font.sono_light)),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
        }
    }, actions = {
        Row {
            IconButton(modifier = Modifier
                .padding(end = 8.dp)
                .height(24.dp)
                .width(24.dp)
                .align(alignment = Alignment.CenterVertically),
                onClick = { onSearchClicked.invoke() }) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                )
            }
            IconButton(modifier = Modifier
                .padding(end = 8.dp)
                .height(24.dp)
                .width(24.dp)
                .align(alignment = Alignment.CenterVertically),
                onClick = { onFilterClicked.invoke() }) {
                Icon(
                    painter = painterResource(R.drawable.ic_filter),
                    contentDescription = null,
                )
            }
        }
    })
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ToolbarUiKitPreview() {
    ToolbarUiKit(onSearchClicked = {}, onFilterClicked = {}, onAboutClicked = {})
}
