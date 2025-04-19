package com.arj.uikit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset

@Composable
fun ShowWithAnimation(
    isVisible: Boolean,
    enterTransition: EnterTransition = slideInVertically(
        initialOffsetY = { it },
        animationSpec = spring(
            stiffness = Spring.StiffnessMediumLow,
            visibilityThreshold = IntOffset.VisibilityThreshold,
        ),
    ),
    exitTransition: ExitTransition = slideOutVertically(
        targetOffsetY = { it },
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            visibilityThreshold = IntOffset.VisibilityThreshold,
        ),
    ),
    content: @Composable() AnimatedVisibilityScope.() -> Unit,
) {
    AnimatedVisibility(
        modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer),
        visible = isVisible,
        enter = enterTransition,
        exit = exitTransition,
        content = content,
    )
}