package com.labs.data.connectivity

import android.net.ConnectivityManager

interface ConnectivityManager {
    fun registerCallback(): ConnectivityManager.NetworkCallback

    fun registerInstance()

    fun clearInstance()
}