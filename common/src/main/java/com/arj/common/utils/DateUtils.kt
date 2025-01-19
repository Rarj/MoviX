package com.arj.common.utils

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

enum class ReleaseStatus {
    RELEASED {
        override fun backgroundColor(): Long = 0xFF009688
        override fun status(): String = "Released"
    },
    UPCOMING {
        override fun backgroundColor(): Long = 0xFFFF5722
        override fun status(): String = "Upcoming"
    };

    abstract fun backgroundColor(): Long
    abstract fun status(): String
}

object DateUtils {
    /**
     * [getReleaseStatus] is responsible to decide either Release Status is [ReleaseStatus.RELEASED]
     * or [ReleaseStatus.UPCOMING] by comparing two dates [releaseDate] and [currentLocalDate]
     *
     * @param releaseDate [String] that represent Movie Released Date
     * @param currentLocalDate [LocalDate] that represent as Today. Default is [LocalDate.now]
     * @return [ReleaseStatus]
     */
    fun getReleaseStatus(releaseDate: String, currentLocalDate: LocalDate = LocalDate.now()): ReleaseStatus {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val releasedLocalDate = LocalDate.parse(releaseDate, inputFormatter)

        return ReleaseStatus.RELEASED.takeIf {
            releasedLocalDate.isEqual(currentLocalDate) || currentLocalDate.isAfter(releasedLocalDate)
        } ?: ReleaseStatus.UPCOMING
    }
}
