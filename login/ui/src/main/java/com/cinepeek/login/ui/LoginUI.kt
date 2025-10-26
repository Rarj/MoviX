package com.cinepeek.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.cinepeek.uikit.R as RUiKit

@Composable
fun LoginScreen(
	viewModel: LoginViewModel = hiltViewModel(),
) {
	val state by viewModel.state.collectAsState()

	LoginUI(
		state = state,
		onUsernameChange = viewModel::onUsernameChange,
		onPasswordChange = viewModel::onPasswordChange,
		onLogin = {
			viewModel.loginAsUser(
				username = state.username.orEmpty(),
				password = state.password.orEmpty(),
			)
		}
	)
}

@Composable
private fun LoginUI(
	state: LoginState,
	onUsernameChange: (String) -> Unit,
	onPasswordChange: (String) -> Unit,
	onLogin: () -> Unit,
) {
	ConstraintLayout(
		modifier = Modifier
			.fillMaxSize()
			.background(color = MaterialTheme.colorScheme.primaryContainer)
	) {
		val (
			headerView,
			usernameView,
			passwordView,
			buttonView,
		) = createRefs()

		Text(
			modifier = Modifier
				.constrainAs(headerView) {
					top.linkTo(parent.top)
					start.linkTo(parent.start)
					end.linkTo(parent.end)
				}
				.padding(16.dp)
				.fillMaxWidth()
				.semantics {
					contentDescription = "Login Header"
				},
			textAlign = TextAlign.Start,
			text = "Hey,\nLogin Now!",
			color = MaterialTheme.colorScheme.onPrimaryContainer,
			fontSize = 24.sp,
			fontFamily = FontFamily(Font(resId = RUiKit.font.sono_bold))
		)

		OutlinedTextField(
			modifier = Modifier
				.constrainAs(usernameView) {
					top.linkTo(headerView.bottom)
					start.linkTo(parent.start)
					end.linkTo(parent.end)
				}
				.fillMaxWidth()
				.padding(horizontal = 16.dp),
			value = state.username.orEmpty(),
			onValueChange = onUsernameChange,
			placeholder = {
				Text(
					fontSize = 14.sp,
					text = "Username",
					color = MaterialTheme.colorScheme.secondary,
					fontFamily = FontFamily(Font(resId = RUiKit.font.sono_regular)),
				)
			},
			textStyle = TextStyle(
				fontSize = 14.sp,
				color = MaterialTheme.colorScheme.onPrimaryContainer,
				fontFamily = FontFamily(Font(resId = RUiKit.font.sono_regular))
			),
			shape = RoundedCornerShape(8.dp),
			maxLines = 1,
			keyboardOptions = KeyboardOptions(
				imeAction = ImeAction.Done,
				capitalization = KeyboardCapitalization.Words,
			),
			trailingIcon = {
				if (state.username?.isBlank()?.not() == true) {
					ClearTextIcon(state.username) {
						onUsernameChange.invoke("")
					}
				}
			}
		)

		OutlinedTextField(
			modifier = Modifier
				.constrainAs(passwordView) {
					top.linkTo(usernameView.bottom)
					start.linkTo(parent.start)
					end.linkTo(parent.end)
				}
				.fillMaxWidth()
				.padding(horizontal = 16.dp)
				.padding(top = 8.dp),
			value = state.password.orEmpty(),
			onValueChange = onPasswordChange,
			placeholder = {
				Text(
					fontSize = 14.sp,
					text = "Password",
					color = MaterialTheme.colorScheme.secondary,
					fontFamily = FontFamily(Font(resId = RUiKit.font.sono_regular)),
				)
			},
			textStyle = TextStyle(
				fontSize = 14.sp,
				color = MaterialTheme.colorScheme.onPrimaryContainer,
				fontFamily = FontFamily(Font(resId = RUiKit.font.sono_regular))
			),
			shape = RoundedCornerShape(8.dp),
			maxLines = 1,
			keyboardOptions = KeyboardOptions(
				imeAction = ImeAction.Done,
				capitalization = KeyboardCapitalization.Words,
			),
		)

		Button(
			modifier = Modifier
				.constrainAs(buttonView) {
					top.linkTo(passwordView.bottom)
					start.linkTo(parent.start)
					end.linkTo(parent.end)
					width = Dimension.fillToConstraints
				}
				.padding(top = 16.dp)
				.padding(horizontal = 16.dp),
			shape = RoundedCornerShape(8.dp),
			onClick = onLogin,
			content = {
				Text(
					modifier = Modifier.padding(vertical = 8.dp),
					text = "Login",
				)
			}
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
				painter = painterResource(id = RUiKit.drawable.ic_close),
				contentDescription = "Clear Keyword",
			)
		}
	}
}

@Preview
@Composable
private fun LoginUIPreview() {
	LoginUI(
		state = LoginState("usern", "pwd"),
		onUsernameChange = { },
		onPasswordChange = { },
		onLogin = { },
	)
}