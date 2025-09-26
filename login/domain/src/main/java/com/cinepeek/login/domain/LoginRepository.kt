package com.cinepeek.login.domain

import com.cinepeek.login.domain.mapper.LoginModel
import com.cinepeek.network.state.CinepeekNetworkResult
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

	suspend fun getRequestToken(): Flow<CinepeekNetworkResult<LoginModel>>

}