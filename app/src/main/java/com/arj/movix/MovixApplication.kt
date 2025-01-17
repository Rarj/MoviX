package com.arj.movix

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovixApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
    }

}