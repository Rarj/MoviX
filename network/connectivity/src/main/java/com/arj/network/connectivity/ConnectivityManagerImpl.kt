package com.arj.network.connectivity

import android.content.Context
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
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

                if (checkConnectionStatus()) onShow()
            }

            override fun onLost(network: Network) {
                super.onLost(network)

                if (checkConnectionStatus()) onShow()
            }

            override fun onUnavailable() {
                super.onUnavailable()

                if (checkConnectionStatus()) onShow()
            }
        }

        return callback
    }

    override fun registerInstance() {
        connectivityManager = context.getSystemService(android.net.ConnectivityManager::class.java)
        connectivityManager.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) registerDefaultNetworkCallback(callback)
            else registerNetworkCallback(NetworkRequest.Builder().build(), callback)
        }
    }

    override fun checkConnectionStatus(): Boolean {
        return !isCellularTransportEnabled() && !isWifiTransportEnabled()
    }

    private fun isCellularTransportEnabled() = connectivityManager.activeNetwork != null &&
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                ?.hasTransport(android.net.NetworkCapabilities.TRANSPORT_CELLULAR) ?: false

    private fun isWifiTransportEnabled() = connectivityManager.activeNetwork != null &&
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                ?.hasTransport(android.net.NetworkCapabilities.TRANSPORT_WIFI) ?: false

    override fun clearInstance() {
        callback.let {
            connectivityManager.unregisterNetworkCallback(it)
        }
    }
}
