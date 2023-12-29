package com.saddict.djrest.products.data.manager.usecases

import com.saddict.djrest.products.data.sources.LocalDataSource
import com.saddict.djrest.products.model.local.ProductFavourites
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavouritesUseCase @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    fun getAllFavourites(): Flow<List<ProductFavourites>> {
        return localDataSource.getAllFavourites()
    }
}