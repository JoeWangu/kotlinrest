package com.saddict.djrest.data.manager

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saddict.djrest.model.local.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(product: List<ProductEntity>)

    @Query("SELECT * from products WHERE id = :id")
    fun getProduct(id: Int): Flow<ProductEntity>

    @Query("SELECT * from products ORDER BY id ASC")
    fun getAllProducts(): Flow<List<ProductEntity>>

//    @Query("DELETE FROM products")
//    suspend fun deleteAllProducts()

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