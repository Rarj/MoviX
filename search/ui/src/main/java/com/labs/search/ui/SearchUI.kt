package com.labs.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.constraintlayout.compose.ConstraintLayout
import com.labs.uikit.appearance.ColorGray
import com.labs.uikit.appearance.ColorPrimary
import com.labs.uikit.appearance.ColorWhite
import com.labs.uikit.R as RUiKit

@Composable
fun SearchUI(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(color = ColorPrimary)
    ) {
        val (topBar) = createRefs()
        val query = remember { mutableStateOf("") }

        Row(
            modifier = Modifier
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .padding(top = 32.dp, end = 16.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically),
                onClick = { onBack.invoke() }
            ) {
                Icon(
                    painter = painterResource(id = RUiKit.drawable.ic_back),
                    tint = ColorWhite,
                    contentDescription = null,
                )
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterVertically),
                value = query.value,
                onValueChange = {
                    query.value = it
                },
                placeholder = {
                    Text(
                        text = "Search Movie...",
                        color = ColorGray,
                        fontFamily = FontFamily(Font(resId = RUiKit.font.sono_regular)),
                    )
                },
                trailingIcon = {
                    ClearTextIcon(query.value) { clearedText ->
                        query.value = clearedText
                    }
                },
                textStyle = TextStyle(
                    color = ColorWhite,
                ),
                shape = RoundedCornerShape(8.dp),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Words,
                ),
            )
        }
    }
}

@Composable
private fun ClearTextIcon(
    query: String,
    onClearedText: (String) -> Unit
) {
    if (query.isBlank().not()) {
        IconButton(onClick = { onClearedText.invoke("") }) {
            Icon(
                painter = painterResource(id = RUiKit.drawable.ic_close),
                contentDescription = null,
                tint = ColorWhite,
            )
        }
    }
}

@Preview
@Composable
private fun SearchUIPreview() {
    SearchUI(
        onBack = {}
    )
}
