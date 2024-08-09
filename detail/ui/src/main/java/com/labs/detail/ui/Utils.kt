package com.labs.detail.ui

import com.labs.data.BuildConfig

internal fun getBackdropUrl(path: String?) = buildString {
    append(BuildConfig.BACKDROP_IMAGE_BASE_URL)
    append(path)
}.ifEmpty { "" }