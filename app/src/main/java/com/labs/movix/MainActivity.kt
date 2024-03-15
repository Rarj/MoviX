package com.labs.movix

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.labs.data.connectivity.ConnectivityManager
import com.labs.movix.databinding.ActivityMainBinding
import com.labs.uikit.NoInternetAccess
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    private lateinit var binding: ActivityMainBinding
    private val connectivityViewModel by viewModels<ConnectivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.composePreview.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

                if (connectivityViewModel.connectivityState.collectAsState().value.isShowNoInternetAccessDialog) {
                    NoInternetAccess {
                        Log.e("NO_INTERNET_ACCESS: ", "onCreate: onDismiss() invoked")
                    }
                }
            }
        }

        connectivityManager.apply {
            registerCallback(
                onShow = {
                    connectivityViewModel.showNoInternetAccessDialog()
                },
                onHide = {
                    connectivityViewModel.hideNoInternetAccessDialog()
                })
            registerInstance()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        connectivityManager.clearInstance()
    }

}
