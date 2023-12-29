package com.saddict.djrest.products.di

import com.saddict.djrest.products.data.sources.ApiRepository
import com.saddict.djrest.products.data.sources.remote.OnlineAppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMyRepository(myRepoImpl: OnlineAppRepository): ApiRepository
}