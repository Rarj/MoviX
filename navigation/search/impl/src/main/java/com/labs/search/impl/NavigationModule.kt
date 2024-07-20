package com.labs.search.impl

import com.labs.search.controller.Navigation
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