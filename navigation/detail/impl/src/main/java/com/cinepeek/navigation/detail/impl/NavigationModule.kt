package com.cinepeek.navigation.detail.impl

import com.cinepeek.navigation.detail.controller.Navigation
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