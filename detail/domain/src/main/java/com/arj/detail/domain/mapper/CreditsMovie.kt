package com.arj.detail.domain.mapper

data class CreditsMovie(
    val id: Int,
    val casts: List<Cast>?,
    val crews: List<Crew>?,
)

data class Cast(
    val id: Int,
    val name: String,
    val profilePath: String?,
    val gender: String?,
    val department: String?,
)

data class Crew(
    val id: Int,
    val name: String,
    val profilePath: String?,
    val gender: String?,
    val department: String?,
)
