package com.cinepeek.login.api.request

import com.google.gson.annotations.SerializedName

data class LoginUserRequest(
	val username: String,
	val password: String,
	@SerializedName("request_token")
	val token: String,
)
