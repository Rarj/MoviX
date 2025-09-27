package com.cinepeek.login.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun LoginScreen() {
	LoginUI()
}

@Composable
private fun LoginUI() {
	ConstraintLayout(
		modifier = Modifier.fillMaxSize()
	) {
		val (text) = createRefs()
		Text(
			modifier = Modifier.constrainAs(text) {
				top.linkTo(parent.top)
				bottom.linkTo(parent.bottom)
				start.linkTo(parent.start)
				end.linkTo(parent.end)
			}, text = "Login"
		)
	}
}

@Preview
@Composable
private fun LoginUIPreview() {
	LoginUI()
}