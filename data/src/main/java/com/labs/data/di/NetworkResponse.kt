package com.labs.data.di

import com.google.gson.annotations.SerializedName

data class NetworkResponse<T : Any>(
    val page: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    val results: List<T>,
)