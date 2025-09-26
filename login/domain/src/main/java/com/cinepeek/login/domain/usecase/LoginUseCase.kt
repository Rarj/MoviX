package com.cinepeek.login.domain.usecase

import com.cinepeek.login.domain.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
	private val loginRepository: LoginRepository,
) {

	suspend fun invoke() = loginRepository.getRequestToken()

}
