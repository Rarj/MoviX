package com.cinepeek.login.impl

import com.cinepeek.login.api.LoginService
import com.cinepeek.login.domain.LoginRepository
import com.cinepeek.login.domain.mapper.LoginModel
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

	override suspend fun getRequestToken(): Flow<CinepeekNetworkResult<LoginModel>> {
		return safeCall(dispatcher) {
			service.getRequestToken().toLoginModel()
		}
	}
}
