package com.cinepeek.login.domain

import com.cinepeek.login.domain.mapper.LoginModel
import com.cinepeek.login.domain.mapper.UserTokenModel
import com.cinepeek.network.state.CinepeekNetworkResult
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

	suspend fun generateGuestToken(): Flow<CinepeekNetworkResult<LoginModel>>

	suspend fun generateUserToken(): Flow<CinepeekNetworkResult<LoginModel>>

	suspend fun validateUserToken(
		username: String,
		password: String,
		token: String,
	): Flow<CinepeekNetworkResult<UserTokenModel>>

}