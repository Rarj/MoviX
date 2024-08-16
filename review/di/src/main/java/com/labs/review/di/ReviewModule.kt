package com.labs.review.di

import com.labs.review.api.ReviewService
import com.labs.review.impl.ReviewRepository
import com.labs.review.impl.mapper.ReviewRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ReviewModule {

    @Provides
    fun provideReviewRepository(
        service: ReviewService
    ): ReviewRepository {
        return ReviewRepositoryImpl(service)
    }

}
