package com.cinepeek.login.api

import com.cinepeek.login.api.response.RequestTokenResponse
import com.cinepeek.network.shared.NetworkResponse
import retrofit2.http.GET

interface LoginService {

	@GET("authentication/token/new")
	suspend fun getRequestToken(): NetworkResponse<RequestTokenResponse>

}