package com.arj.network

import androidx.lifecycle.ViewModel
import com.arj.network.connectivity.ConnectivityManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ConnectivityManagerViewModel @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : ViewModel() {

    private val _state = MutableStateFlow(ConnectivityManagerState())
    val state get() = _state.asStateFlow()

    fun register() {
        connectivityManager.apply {
            registerCallback(onShow = {
                setConnectedStatus(false)
            }, onHide = {
                setConnectedStatus(true)
            })
            registerInstance()
        }
    }

    fun clearInstance() {
        connectivityManager.clearInstance()
    }

    private fun setConnectedStatus(status: Boolean) {
        _state.update {
            it.copy(
                connectionIsConnected = status,
            )
        }
    }

    fun getInitializedConnection() {
        _state.update {
            it.copy(
                connectionIsConnected = connectivityManager.checkConnectionStatus(),
            )
        }
    }

}