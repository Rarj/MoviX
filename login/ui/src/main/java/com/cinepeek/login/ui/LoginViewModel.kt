package com.cinepeek.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinepeek.login.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
	private val loginUseCase: LoginUseCase,
) : ViewModel() {

	fun requestToken() {
		viewModelScope.launch {
			loginUseCase.invoke().collectLatest {

			}
		}
	}

}