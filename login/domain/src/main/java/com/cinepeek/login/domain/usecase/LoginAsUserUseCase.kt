package com.cinepeek.login.domain.usecase

import com.cinepeek.login.domain.LoginRepository
import com.cinepeek.network.state.CinepeekNetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class LoginAsUserUseCase @Inject constructor(
	private val loginRepository: LoginRepository,
) {

	@OptIn(ExperimentalCoroutinesApi::class)
	suspend fun invoke(
		username: String,
		password: String,
	) = loginAsUser(username, password)

	private suspend fun loginAsUser(
		username: String,
		password: String,
	): Flow<Boolean> {
		return loginRepository.generateUserToken().transform { state ->
			when (state) {
				is CinepeekNetworkResult.Success -> loginRepository.validateUserToken(
					username = username,
					password = password,
					token = state.value.token,
				).collect {
					emit(it)
				}

				else -> emit(null)
			}
		}.transform { state ->
			when (state) {
				is CinepeekNetworkResult.Success -> emit(state.value.success && state.value.requestToken.isNotEmpty())
				else -> emit(false)
			}
		}
	}

}
