package com.oratakashi.movieq.di

import com.oratakashi.movieq.data.factory.DiscoverFactory
import com.oratakashi.movieq.data.factory.Factory
import com.oratakashi.movieq.data.network.ApiEndpoint
import com.oratakashi.movieq.data.source.DiscoverSource
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
        discoverFactory: DiscoverFactory
    ) : Factory = Factory(
        discoverFactory
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
}