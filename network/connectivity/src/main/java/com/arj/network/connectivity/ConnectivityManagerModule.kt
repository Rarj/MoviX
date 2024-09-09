package com.arj.network.connectivity

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ConnectivityManagerModule {

    @Provides
    fun provideConnectivityManager(
        @ApplicationContext context: Context
    ) : ConnectivityManager {
        return ConnectivityManagerImpl(context)
    }

}