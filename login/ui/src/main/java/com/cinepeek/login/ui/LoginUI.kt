package com.cinepeek.login.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun LoginScreen(
	onLoginAsGuest: () -> Unit,
	onLoginAsUser: (
		username: String,
		password: String
	) -> Unit,
) {
	LoginUI(
		onLoginAsGuest = onLoginAsGuest,
		onLoginAsUser = onLoginAsUser,
	)
}

@Composable
private fun LoginUI(
	onLoginAsGuest: () -> Unit,
	onLoginAsUser: (
		username: String,
		password: String
	) -> Unit,
) {
	ConstraintLayout(
		modifier = Modifier.fillMaxSize()
	) {
		val (text, text1) = createRefs()
		Button(
			modifier = Modifier.constrainAs(text) {
				top.linkTo(parent.top)
				bottom.linkTo(parent.bottom)
				start.linkTo(parent.start)
				end.linkTo(parent.end)
			},
			onClick = onLoginAsGuest,
		) {
			Text(text = "Login as Guest")
		}

		Button(
			modifier = Modifier.constrainAs(text1) {
				top.linkTo(text.bottom)
				bottom.linkTo(parent.bottom)
				start.linkTo(parent.start)
				end.linkTo(parent.end)
			},
			onClick = {
				onLoginAsUser.invoke(
					"rioarjuna",
					"TMDB88!!"
				)
			},
		) {
			Text(text = "Login as User")
		}
	}
}

@Preview
@Composable
private fun LoginUIPreview() {
	LoginUI({}, { _, _ -> })
}