package com.arj.network

data class ConnectivityManagerState(
    val connectionIsConnected: Boolean? = null,
    val onRetry: Boolean = false,
)
