package com.arj.review.di

import com.arj.review.api.ReviewService
import com.arj.review.impl.ReviewRepository
import com.arj.review.impl.mapper.ReviewRepositoryImpl
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
