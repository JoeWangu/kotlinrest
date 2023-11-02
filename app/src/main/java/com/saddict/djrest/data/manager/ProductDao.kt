package com.saddict.djrest.data.manager

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.saddict.djrest.model.local.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Upsert
    suspend fun insertAll(product: List<ProductEntity>)

    @Query("SELECT * from products WHERE id = :id")
    fun getProduct(id: Int): Flow<ProductEntity>

    @Query("SELECT * from products ORDER BY id ASC")
    fun getAllProducts(): Flow<List<ProductEntity>>

//    @Query("SELECT * from products")
//    fun pagingSource(): PagingSource<Int, ProductEntity>

//    @Query("SELECT * from products")
//    fun customPagingSource(): CustomPagingSource

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

//    @Update
//    suspend fun updateProductResponse(productResponse: Products)

//    @Delete
//    suspend fun deleteProductResponse(productResponse: Products)

//    @Query("SELECT * from products")
//    fun getProductResponse(): Flow<ProductsResult>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertProduct(product: List<ProductsResult>)

//    @Update
//    suspend fun updateProduct(product: ProductsResult)

//    @Delete
//    suspend fun deleteProduct(product: ProductsResult)
}