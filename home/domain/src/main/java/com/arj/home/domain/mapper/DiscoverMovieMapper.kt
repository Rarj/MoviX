package com.arj.home.domain.mapper

import com.arj.home.api.response.discover.Movie
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.Period
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit
import java.text.SimpleDateFormat
import java.util.Locale

fun Movie.toDiscoverMovie() = DiscoverMovie(
    id = id,
    posterPath = posterPath,
    genreIds = genreIds,
    title = title,
    overview = overview,
    rating = rating,
    releaseDate = releaseDate.getDateFormatted(),
)

fun String.getDateFormatted(): String {
    if (this.isEmpty()) return ""

//    this.getReleaseStatus()

    val outputFormat = "dd MMM yyyy"
    val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
    val outputFormatter = DateTimeFormatter.ofPattern(outputFormat, Locale.getDefault())

    val date = LocalDateTime.parse(this, inputFormat)
    val formattedDate = date.format(outputFormatter)

    return formattedDate
}

//private val calendar = Calendar.getInstance()
fun String.getReleaseStatus(): String {
//    val releaseDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
//    calendar.timeInMillis = releaseDate?.time ?: 0
//
//    val dayMillis = calendar.get(Calendar.DAY_OF_MONTH)
//    val monthMillis = calendar.get(Calendar.MONTH)
//    val yearMillis = calendar.get(Calendar.YEAR)
//    val currentDateInMillis = dayMillis.plus(monthMillis).plus(yearMillis)
//
//    return releaseDate?.time?.minus(currentDateInMillis).toString()

//    val currentDate = LocalDate.now() // Gets the current date
//    val year = currentDate.year       // Extracts the year
//    val month = currentDate.month     // Extracts the month (as an enum)
//    val day = currentDate.dayOfMonth  // Extracts the day of the month
//
//    val date1 = LocalDate.of(year, month, day)
//    val date2 = LocalDate.of(2025, 1, 16)
//

    val currentDate = LocalDate.now() // Get current date
//    val year = currentDate.year       // Get year
//    val month = currentDate.month     // Get month (enum)
//    val day = currentDate.dayOfMonth  // Get day of month
//
//    println("THREETENYear: $year")
//    println("THREETENMonth: ${String.format(Locale.getDefault(), "%02d", month.value)}")
//    println("THREETENDay: $day")
//
//    val period = Period.between(currentDate, LocalDate.parse(this))
//
//    println("THREETENPeriod: ${period.days}")
//    println("THREETENPeriod: ${period}")


    val releaseDateInMillis = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)?.time ?: 0
    val currentDateInMillis = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(currentDate.toString())?.time ?: 0
    val millis = releaseDateInMillis.minus(currentDateInMillis)
    println("THREETENMillis: $millis")

    return ""
}
