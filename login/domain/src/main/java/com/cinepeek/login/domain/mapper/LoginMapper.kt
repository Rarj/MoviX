package com.cinepeek.login.domain.mapper

import com.cinepeek.login.api.response.RequestTokenResponse

fun RequestTokenResponse.toLoginModel() = LoginModel(this.token)