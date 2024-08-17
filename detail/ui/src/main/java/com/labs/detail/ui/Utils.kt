package com.labs.detail.ui

import com.labs.uikit.BuildConfig

internal fun getBackdropUrl(path: String?) = buildString {
    append(BuildConfig.BACKDROP_IMAGE_BASE_URL)
    append(path)
}.ifEmpty { "" }