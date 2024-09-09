package com.arj.network.connectivity

import android.content.Context
import android.net.Network
import android.os.Build
import android.util.Log
import javax.inject.Inject

class ConnectivityManagerImpl @Inject constructor(
    private val context: Context
) : ConnectivityManager {

    private lateinit var connectivityManager: android.net.ConnectivityManager
    private lateinit var callback: android.net.ConnectivityManager.NetworkCallback

    override fun registerCallback(
        onShow: () -> Unit,
        onHide: () -> Unit,
    ): android.net.ConnectivityManager.NetworkCallback {
        callback = object : android.net.ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)

                onHide()
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)

                onShow()
            }

            override fun onLost(network: Network) {
                super.onLost(network)

                onShow()
            }

            override fun onUnavailable() {
                super.onUnavailable()

                onShow()
            }
        }

        return callback
    }

    override fun registerInstance() {
        connectivityManager = context.getSystemService(android.net.ConnectivityManager::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(callback)
        }
    }

    override fun clearInstance() {
        callback.let {
            connectivityManager.unregisterNetworkCallback(it)
        }
    }
}
