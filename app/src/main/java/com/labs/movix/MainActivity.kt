package com.labs.movix

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.labs.data.connectivity.ConnectivityManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        connectivityManager.apply {
            registerCallback()
            registerInstance()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        connectivityManager.clearInstance()
    }

}
