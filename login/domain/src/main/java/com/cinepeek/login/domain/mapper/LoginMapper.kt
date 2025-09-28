package com.cinepeek.login.domain.mapper

import com.cinepeek.login.api.response.GuestTokenResponse
import com.cinepeek.login.api.response.UserTokenResponse
import com.cinepeek.login.api.response.ValidateUserTokenResponse

fun UserTokenResponse.toLoginModel() = LoginModel(this.token)

fun GuestTokenResponse.toLoginModel() = LoginModel(this.sessionId)

fun ValidateUserTokenResponse.toLoginModel() = UserTokenModel(
	success = this.success,
	expiresAt = this.expiresAt,
	requestToken = this.requestToken,
)