package com.arj.network

sealed class ConnectivityManagerStatus {
    data object Connected: ConnectivityManagerStatus()
    data object Disconnected: ConnectivityManagerStatus()
}