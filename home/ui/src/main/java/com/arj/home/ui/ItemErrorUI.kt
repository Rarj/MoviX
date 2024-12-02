package com.arj.home.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arj.uikit.R
import com.arj.uikit.appearance.ColorSecondaryVariant

@Composable
internal fun ItemErrorUI(
    onRetry: () -> Unit,
) {
    TextButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = { onRetry.invoke() },
    ) {
        Text(
            text = "Retry",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.sono_bold)),
            letterSpacing = 1.sp,
            color = ColorSecondaryVariant,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemErrorUIPreview() {
    ItemErrorUI(
        onRetry = {},
    )
}
