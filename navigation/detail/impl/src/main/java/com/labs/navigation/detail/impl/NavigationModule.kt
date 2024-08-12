package com.labs.navigation.detail.impl

import com.labs.navigation.detail.controller.Navigation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    @Provides
    fun provideNavigationModule(): Navigation {
        return NavigationImpl()
    }

}