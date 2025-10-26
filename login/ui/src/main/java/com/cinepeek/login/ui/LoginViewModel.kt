package com.cinepeek.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinepeek.login.domain.usecase.LoginAsGuestUseCase
import com.cinepeek.login.domain.usecase.LoginAsUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
	private val loginAsGuestUseCase: LoginAsGuestUseCase,
	private val loginAsUserUseCase: LoginAsUserUseCase,
) : ViewModel() {

	private val _state = MutableStateFlow(LoginState("", ""))
	val state get() = _state.asStateFlow()

	internal fun loginAsGuest() {
		viewModelScope.launch {
			loginAsGuestUseCase.invoke().collectLatest {
				it
			}
		}
	}

	fun loginAsUser(
		username: String,
		password: String,
	) {
		viewModelScope.launch {
			loginAsUserUseCase.invoke(
				username = username,
				password = password,
			).collectLatest {
				it
			}
		}
	}

	fun onUsernameChange(username: String) {
		_state.update {
			it.copy(username = username)
		}
	}

	fun onPasswordChange(password: String) {
		_state.update {
			it.copy(password = password)
		}
	}
}