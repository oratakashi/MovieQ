package com.oratakashi.movieq.di

import com.oratakashi.movieq.data.factory.Factory
import com.oratakashi.movieq.data.network.ApiEndpoint
import com.oratakashi.movieq.data.repository.DataRepository
import com.oratakashi.movieq.data.repository.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRemoteRepository(
        disposable: CompositeDisposable,
        endpoint: ApiEndpoint,
        factory: Factory
    ) : RemoteRepository = RemoteRepository(disposable, endpoint, factory)

    @Provides
    @Singleton
    fun provideDataRepository(
        remoteRepository: RemoteRepository
    ) : DataRepository = DataRepository(remoteRepository)
}