package com.oratakashi.movieq.di

import com.oratakashi.movieq.data.repository.DataRepository
import com.oratakashi.movieq.data.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {
    @Binds
    abstract fun bindingRepository(
        dataRepository: DataRepository
    ) : Repository
}