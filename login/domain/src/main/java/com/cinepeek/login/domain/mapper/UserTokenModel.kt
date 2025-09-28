package com.cinepeek.login.domain.mapper

data class UserTokenModel(
	val success: Boolean,
	val expiresAt: String,
	val requestToken: String,
)
