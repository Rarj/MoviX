package com.labs.review.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.labs.uikit.R
import com.labs.uikit.appearance.ColorWhite

@Composable
fun ReviewEmptyUI() {
    Column {
        Image(
            modifier = Modifier
                .wrapContentSize()
                .padding(all = 32.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_no_review),
            contentDescription = "Review Not Found",
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp, start = 16.dp, end = 16.dp),
            text = "Ups, No Review Found!",
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(resId = R.font.sono_medium)),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ReviewEmptyUIPreview() {
    ReviewEmptyUI()
}