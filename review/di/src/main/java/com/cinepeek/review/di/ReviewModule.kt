package com.cinepeek.review.di

import com.cinepeek.network.state.IoDispatcher
import com.cinepeek.review.api.ReviewService
import com.cinepeek.review.domain.ReviewRepository
import com.cinepeek.review.domain.usecase.ReviewUseCase
import com.cinepeek.review.impl.ReviewRepositoryImpl
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
