package com.cinepeek.login.api

import com.cinepeek.login.api.request.LoginUserRequest
import com.cinepeek.login.api.response.GuestTokenResponse
import com.cinepeek.login.api.response.UserTokenResponse
import com.cinepeek.login.api.response.ValidateUserTokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginService {

	@GET("authentication/guest_session/new")
	suspend fun generateGuestToken(): GuestTokenResponse

	@GET("authentication/token/new")
	suspend fun generateUserToken(): UserTokenResponse

	@POST("authentication/token/validate_with_login")
	suspend fun validateUserToken(
		@Body loginUserRequest: LoginUserRequest,
	): ValidateUserTokenResponse

}