package com.arj.detail.ui.tab.caster

import androidx.compose.runtime.Composable
import com.arj.detail.ui.DetailMovieState

@Composable
internal fun CastUI(
    state: DetailMovieState,
) {
    println("castsPERSON:"  + state.casts.toString())
}