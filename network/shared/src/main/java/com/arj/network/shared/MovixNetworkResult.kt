package com.arj.network.shared

sealed class MovixNetworkResult<out T> {
    class Loading<T> : MovixNetworkResult<T>()
    data class Success<out T>(val value: T) : MovixNetworkResult<T>()
    data class Failed(val code: Int? = null, val message: String? = null) : MovixNetworkResult<Nothing>()
}
