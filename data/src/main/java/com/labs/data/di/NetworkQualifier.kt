package com.labs.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkQualifierHomeService

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkQualifierApiService
