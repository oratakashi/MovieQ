package com.oratakashi.movieq.di

import com.oratakashi.movieq.data.factory.DiscoverFactory
import com.oratakashi.movieq.data.factory.Factory
import com.oratakashi.movieq.data.factory.ReviewFactory
import com.oratakashi.movieq.data.network.ApiEndpoint
import com.oratakashi.movieq.data.source.DiscoverSource
import com.oratakashi.movieq.data.source.ReviewSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PaggingModule {
    @Provides
    @Singleton
    fun provideFactory(
        discoverFactory: DiscoverFactory,
        reviewFactory: ReviewFactory
    ) : Factory = Factory(
        discoverFactory,
        reviewFactory
    )

    @Provides
    @Singleton
    fun provideDiscoverSource(
        endpoint: ApiEndpoint,
        disposable: CompositeDisposable
    ) : DiscoverSource = DiscoverSource(endpoint, disposable)

    @Provides
    @Singleton
    fun provideDiscoverFactory(
        discoverSource: DiscoverSource
    ) : DiscoverFactory = DiscoverFactory(discoverSource)

    @Provides
    @Singleton
    fun provideReviewSource(
        endpoint: ApiEndpoint,
        disposable: CompositeDisposable
    ) : ReviewSource = ReviewSource(endpoint, disposable)

    @Provides
    @Singleton
    fun provideReviewFactory(
        reviewSource: ReviewSource
    ) : ReviewFactory = ReviewFactory(reviewSource)
}