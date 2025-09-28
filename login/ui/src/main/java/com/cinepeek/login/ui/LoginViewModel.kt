package com.cinepeek.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinepeek.login.domain.usecase.LoginAsGuestUseCase
import com.cinepeek.login.domain.usecase.LoginAsUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
	private val loginAsGuestUseCase: LoginAsGuestUseCase,
	private val loginAsUserUseCase: LoginAsUserUseCase,
) : ViewModel() {

	fun loginAsGuest() {
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

}