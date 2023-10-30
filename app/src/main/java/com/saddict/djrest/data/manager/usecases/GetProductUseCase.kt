package com.saddict.djrest.data.manager.usecases

import com.saddict.djrest.data.manager.AppRepository
import com.saddict.djrest.model.local.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val appRepository: AppRepository) {
    //    suspend operator fun invoke(productEntity: List<ProductEntity>){
//        appRepository.insertAll(productEntity)
//    }
    fun getProduct(id: Int): Flow<ProductEntity?> {
        return appRepository.getProduct(id)
    }
}