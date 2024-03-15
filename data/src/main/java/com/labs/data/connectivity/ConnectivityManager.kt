package com.labs.data.connectivity

import android.net.ConnectivityManager

interface ConnectivityManager {
    fun registerCallback(
        onShow: () -> Unit,
        onHide: () -> Unit,
    ): ConnectivityManager.NetworkCallback

    fun registerInstance()

    fun clearInstance()
}