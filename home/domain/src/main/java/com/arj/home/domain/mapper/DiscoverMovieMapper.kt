package com.arj.home.domain.mapper

import com.arj.common.utils.DateUtils
import com.arj.common.utils.ReleaseStatus
import com.arj.home.api.response.discover.Movie
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

fun Movie.toDiscoverMovie(): DiscoverMovie {
    val releaseStatus = DateUtils.getReleaseStatus(releaseDate).status()
    val date = releaseDate.getDateFormatted().takeIf {
        releaseStatus != ReleaseStatus.RELEASED.status()
    }.orEmpty()
    return DiscoverMovie(
        id = id,
        posterPath = posterPath,
        genreIds = genreIds,
        title = title,
        overview = overview,
        rating = rating,
        releaseDate = date,
        releaseStatus = DateUtils.getReleaseStatus(releaseDate).status(),
        releaseStatusBackground = DateUtils.getReleaseStatus(releaseDate).backgroundColor(),
    )
}

fun String.getDateFormatted(): String {
    if (this.isEmpty()) return ""

    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    val parsedDate = LocalDate.parse(this, inputFormatter)

    return parsedDate.format(outputFormatter)
}
