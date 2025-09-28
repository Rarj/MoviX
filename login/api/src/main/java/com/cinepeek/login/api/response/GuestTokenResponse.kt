package com.cinepeek.login.api.response

import com.google.gson.annotations.SerializedName

data class GuestTokenResponse(
	@SerializedName("guest_session_id")
	val sessionId: String,
)