package com.saddict.djrest.products.data.manager.usecases

import com.saddict.djrest.products.data.sources.LocalDataSource
import com.saddict.djrest.products.model.local.ProductFavourites
import javax.inject.Inject

class InsertFavouriteUseCase @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    suspend fun insertFavourite(favourite: ProductFavourites) {
        return localDataSource.insertFavourite(favourite)
    }
}