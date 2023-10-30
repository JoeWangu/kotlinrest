package com.saddict.djrest.data.manager.usecases

import com.saddict.djrest.data.manager.AppRepository
import com.saddict.djrest.model.local.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(private val appRepository: AppRepository) {
    fun getAllProducts(): Flow<List<ProductEntity>> {
        return appRepository.getAllProducts()
    }
}