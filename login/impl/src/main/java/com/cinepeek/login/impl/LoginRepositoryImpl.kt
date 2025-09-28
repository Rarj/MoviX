package com.cinepeek.login.impl

import com.cinepeek.login.api.LoginService
import com.cinepeek.login.api.request.LoginUserRequest
import com.cinepeek.login.domain.LoginRepository
import com.cinepeek.login.domain.mapper.LoginModel
import com.cinepeek.login.domain.mapper.UserTokenModel
import com.cinepeek.login.domain.mapper.toLoginModel
import com.cinepeek.network.state.CinepeekNetworkResult
import com.cinepeek.network.state.safeCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
	private val service: LoginService,
	private val dispatcher: CoroutineDispatcher,
) : LoginRepository {

	override suspend fun generateGuestToken(): Flow<CinepeekNetworkResult<LoginModel>> {
		return safeCall(dispatcher) {
			service.generateGuestToken().toLoginModel()
		}
	}

	override suspend fun generateUserToken(): Flow<CinepeekNetworkResult<LoginModel>> {
		return safeCall(dispatcher) {
			service.generateUserToken().toLoginModel()
		}
	}

	override suspend fun validateUserToken(
		username: String,
		password: String,
		token: String
	): Flow<CinepeekNetworkResult<UserTokenModel>> {
		return safeCall(dispatcher) {
			val request = LoginUserRequest(
				username = username,
				password = password,
				token = token,
			)
			service.validateUserToken(request).toLoginModel()
		}
	}
}
