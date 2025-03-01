package com.arj.common.utils

import kotlin.math.roundToInt

fun getRating(rating: Double?): String? {
    return if (rating == null || rating <= 0.0) null
    else buildString {
        append(rating.times(10.0).roundToInt().div(10.0))
        append("/10")
    }
}