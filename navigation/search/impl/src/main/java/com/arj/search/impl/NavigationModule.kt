package com.arj.search.impl

import com.arj.search.controller.Navigation
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