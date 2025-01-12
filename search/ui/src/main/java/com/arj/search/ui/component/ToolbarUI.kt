package com.arj.search.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arj.uikit.R

@Composable
internal fun ToolbarUI(
    modifier: Modifier = Modifier,
    keyword: String,
    onValueChange: (String) -> Unit,
    onBack: () -> Unit,
) {
    Row(
        modifier = modifier
            .wrapContentSize()
            .padding(top = 62.dp, end = 16.dp)
    ) {
        IconButton(
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically),
            onClick = onBack::invoke,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back to Home page",
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.CenterVertically),
            value = keyword,
            onValueChange = onValueChange::invoke,
            placeholder = {
                Text(
                    fontSize = 14.sp,
                    text = "Search Movie...",
                    color = MaterialTheme.colorScheme.secondary,
                    fontFamily = FontFamily(Font(resId = R.font.sono_regular)),
                )
            },
            trailingIcon = {
                ClearTextIcon(keyword) {
                    onValueChange.invoke("")
                }
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontFamily = FontFamily(Font(resId = R.font.sono_regular))
            ),
            shape = RoundedCornerShape(percent = 50),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                capitalization = KeyboardCapitalization.Words,
            ),
        )
    }
}

@Composable
private fun ClearTextIcon(
    keyword: String,
    onClearedText: () -> Unit
) {
    if (keyword.isBlank().not()) {
        IconButton(onClick = { onClearedText.invoke() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "Clear Keyword",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ToolbarUIPreview() {
    ToolbarUI(
        keyword = "Avengers",
        onValueChange = { },
        onBack = { },
    )
}