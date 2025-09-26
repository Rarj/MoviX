package com.cinepeek.login.api.response

import com.google.gson.annotations.SerializedName

data class RequestTokenResponse(
	@SerializedName("request_token")
	val token: String,
)
