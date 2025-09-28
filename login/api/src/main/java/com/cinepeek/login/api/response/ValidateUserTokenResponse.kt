package com.cinepeek.login.api.response

import com.google.gson.annotations.SerializedName

data class ValidateUserTokenResponse(
	@SerializedName("success")
	val success: Boolean,
	@SerializedName("expires_at")
	val expiresAt: String,
	@SerializedName("request_token")
	val requestToken: String,
)
