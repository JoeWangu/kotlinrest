package com.saddict.djrest.products.data.manager.usecases

import com.saddict.djrest.products.data.sources.LocalDataSource
import com.saddict.djrest.products.model.local.ProductEntity
import javax.inject.Inject

class InsertAllUseCase @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    suspend fun insertAll(productEntity: List<ProductEntity>) {
        localDataSource.insertAll(productEntity)
    }
}