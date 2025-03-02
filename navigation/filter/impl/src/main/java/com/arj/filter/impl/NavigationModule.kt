package com.arj.filter.impl

import com.arj.filter.controller.Navigation
import com.arj.filter.impl.state.FilterViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {
    @Provides
    fun provideNavigationModule(viewModel: FilterViewModel): Navigation {
        return NavigationImpl(viewModel)
    }
}