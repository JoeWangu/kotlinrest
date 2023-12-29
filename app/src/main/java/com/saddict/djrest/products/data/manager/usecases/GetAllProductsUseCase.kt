package com.saddict.djrest.products.data.manager.usecases

import com.saddict.djrest.products.data.sources.LocalDataSource
import com.saddict.djrest.products.model.local.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    fun getAllProducts(): Flow<List<ProductEntity>> {
        return localDataSource.getAllProducts()
    }
}