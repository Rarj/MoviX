package com.arj.search.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.arj.uikit.R

@Composable
internal fun ConstraintLayoutScope.EmptyState(
    modifier: Modifier = Modifier,
    message: String,
) {
    this.apply {
        Column(
            modifier = modifier
                .padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(all = 32.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_search_empty),
                contentDescription = "Review Not Found",
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp, start = 16.dp, end = 16.dp),
                text = message,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(resId = R.font.sono_medium)),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyStateUIPreview() {
    ConstraintLayout {
        EmptyState(
            message = "Ups, we got some error.",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IdleStateUIPreview() {
    ConstraintLayout {
        EmptyState(
            message = "Find out your movie!",
        )
    }
}
