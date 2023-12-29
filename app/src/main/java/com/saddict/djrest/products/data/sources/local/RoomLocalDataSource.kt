package com.saddict.djrest.products.data.sources.local

import com.saddict.djrest.products.data.manager.ProductDao
import com.saddict.djrest.products.data.sources.LocalDataSource
import com.saddict.djrest.products.model.local.ProductEntity
import com.saddict.djrest.products.model.local.ProductFavourites
import kotlinx.coroutines.flow.Flow


class RoomLocalDataSource(private val productDao: ProductDao) : LocalDataSource {
//    override suspend fun insertAll(products: List<ProductEntity>) = productDao.insertAll(products)
//    override fun getAllProducts(): Flow<List<ProductEntity>> = productDao.getAllProducts()
//    override fun getProduct(id: Int): Flow<ProductEntity?> = productDao.getProduct(id)

    override suspend fun insertAll(products: List<ProductEntity>) {
        return productDao.insertAll(products)
    }

    override fun getAllProducts(): Flow<List<ProductEntity>> {
        return productDao.getAllProducts()
    }

    override fun getProduct(id: Int): Flow<ProductEntity?> {
        return productDao.getProduct(id)
    }

    override suspend fun insertFavourite(favourite: ProductFavourites) {
        return productDao.insertFavourite(favourite)
    }

    override fun getAllFavourites(): Flow<List<ProductFavourites>> {
        return productDao.getFavourites()
    }
//    override fun loadAllPaged(): PagingSource<Int, ProductEntity> {
//        return productDao.pagingSource()
//    }
//    override fun customLoadAllPaged(): CustomPagingSource {
//        return productDao.customPagingSource()
//    }
//    override suspend fun insertProduct(product: List<ProductsResult>) =
//        productDao.insertProduct(product)
//    override suspend fun deleteProduct(product: ProductsResult) = productDao.deleteProduct(product)
//    override suspend fun updateProduct(product: ProductsResult) = productDao.updateProduct(product)
}