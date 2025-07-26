package com.cinepeek.network.state

sealed class CinepeekNetworkResult<out T> {
    class Loading<T> : CinepeekNetworkResult<T>()
    data class Success<out T>(val value: T) : CinepeekNetworkResult<T>()
    data class Failed(val code: Int? = null, val message: String? = null) :
        CinepeekNetworkResult<Nothing>()
}
