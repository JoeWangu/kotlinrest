package com.saddict.djrest.products.di

import android.content.Context
import androidx.room.Room
import com.saddict.djrest.products.data.manager.ProductDao
import com.saddict.djrest.products.data.sources.LocalDataSource
import com.saddict.djrest.products.data.sources.local.ProductDatabase
import com.saddict.djrest.products.data.sources.local.RoomLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    fun provideProductDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room.databaseBuilder(context, ProductDatabase::class.java, "product_database")
            .build()
    }

    @Provides
    fun provideProductDao(productDatabase: ProductDatabase): ProductDao {
        return productDatabase.productDao()
    }

    @Provides
    fun provideLocalDataSource(productDao: ProductDao): LocalDataSource {
        return RoomLocalDataSource(productDao)
    }
}