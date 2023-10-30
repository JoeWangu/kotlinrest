package com.saddict.djrest.data.manager.usecases

import com.saddict.djrest.data.manager.AppRepository
import com.saddict.djrest.model.local.ProductEntity
import javax.inject.Inject

class InsertAllUseCase @Inject constructor(private val appRepository: AppRepository) {
    suspend fun insertAll(productEntity: List<ProductEntity>) {
        appRepository.insertAll(productEntity)
    }
}