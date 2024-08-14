package com.labs.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkQualifierSearchService

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkQualifierHomeService

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkQualifierDetailMovieService

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkQualifierReviewService
