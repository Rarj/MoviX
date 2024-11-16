package com.arj.review.di

import com.arj.network.state.IoDispatcher
import com.arj.review.api.ReviewService
import com.arj.review.domain.ReviewRepository
import com.arj.review.domain.usecase.ReviewUseCase
import com.arj.review.impl.ReviewRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ReviewModule {

    @Provides
    fun provideReviewRepository(
        service: ReviewService,
        @IoDispatcher dispatcher: CoroutineDispatcher,
    ): ReviewRepository {
        return ReviewRepositoryImpl(service, dispatcher)
    }

    @Provides
    fun provideReviewService(retrofit: Retrofit): ReviewService {
        return retrofit.create(ReviewService::class.java)
    }

    @Provides
    fun provideReviewUseCase(reviewRepository: ReviewRepository) =
        ReviewUseCase(reviewRepository)

}
