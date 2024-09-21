package com.arj.network.connectivity

import android.net.ConnectivityManager

interface ConnectivityManager {
    fun registerCallback(
        onDisconnected: () -> Unit,
        onConnected: () -> Unit,
    ): ConnectivityManager.NetworkCallback

    fun registerInstance()

    fun clearInstance()

    fun checkConnectionStatus(): Boolean
}