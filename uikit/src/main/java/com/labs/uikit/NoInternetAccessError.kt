package com.labs.uikit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoInternetAccess(onDismiss: () -> Unit) {
    val bottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = bottomSheetState,
        onDismissRequest = { onDismiss() },
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (title, imageNoInternet, retryButton) = createRefs()

            ImageNoInternet(
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(imageNoInternet) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
            )

            TextTitle(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .constrainAs(title) {
                        top.linkTo(imageNoInternet.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
            )

            RetryButton(
                modifier = Modifier
                    .width(100.dp)
                    .padding(top = 24.dp)
                    .constrainAs(retryButton) {
                        top.linkTo(title.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
            ) {

            }

        }
    }
}

@Composable
private fun TextTitle(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = "Please check your connection",
        fontFamily = FontFamily(Font(R.font.sono_bold)),
    )
}

@Composable
private fun RetryButton(modifier: Modifier, onRetry: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = { onRetry() }) {
        Text(text = "Retry")
    }
}

@Composable
private fun ImageNoInternet(modifier: Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.illustration_no_internet_access),
        contentDescription = "No Internet Access"
    )
}

@Preview
@Composable
fun NoInternetAccessPreview() {
    NoInternetAccess(onDismiss = { })
}
