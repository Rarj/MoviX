package com.arj.filter.impl

import com.arj.filter.controller.FilterState
import com.arj.filter.controller.Navigation
import com.arj.filter.impl.state.FilterViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class NavigationImpl @Inject constructor(
    val viewModel: FilterViewModel,
) : Navigation {
    override val state: StateFlow<FilterState>
        get() = viewModel.state

    override fun showBottomSheet() = viewModel.showFilterBottomSheet()

    override fun hideBottomSheet() = viewModel.hideFilterBottomSheet()
}