package com.saddict.djrest.products.data.manager.usecases

import com.saddict.djrest.products.data.sources.LocalDataSource
import com.saddict.djrest.products.model.local.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    //    suspend operator fun invoke(productEntity: List<ProductEntity>){
//        appRepository.insertAll(productEntity)
//    }
    fun getProduct(id: Int): Flow<ProductEntity?> {
        return localDataSource.getProduct(id)
    }
}