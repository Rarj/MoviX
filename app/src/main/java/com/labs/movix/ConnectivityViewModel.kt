package com.labs.movix

import androidx.lifecycle.ViewModel
import com.labs.data.connectivity.ConnectivityManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ConnectivityViewModel @Inject constructor() : ViewModel() {

    private val _connectivityState = MutableStateFlow(ConnectivityState())
    internal val connectivityState get() = _connectivityState.asStateFlow()

    fun showNoInternetAccessDialog() {
        _connectivityState.update {
            it.copy(
                isShowNoInternetAccessDialog = true,
            )
        }
    }

    fun hideNoInternetAccessDialog() {
        _connectivityState.update {
            it.copy(
                isShowNoInternetAccessDialog = false,
            )
        }
    }

}

internal data class ConnectivityState(
    val isShowNoInternetAccessDialog: Boolean = false,
)