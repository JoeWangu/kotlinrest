package com.saddict.djrest.products.data.manager

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.saddict.djrest.products.model.local.ProductEntity
import com.saddict.djrest.products.model.local.ProductFavourites
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

    @Query("SELECT * from products ORDER BY id DESC")
    fun getAllDesc(): List<ProductEntity>

    @Query("SELECT * from products ORDER BY id DESC")
    suspend fun getAllToPage(): List<ProductEntity>

    @Query("SELECT * from products")
    fun pagingSource(): PagingSource<Int, ProductEntity>

//    @Query("SELECT * from products")
//    fun customPagingSource(): CustomPagingSource

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: ProductFavourites)

    @Query("SELECT * FROM productfavourites")
    fun getFavourites(): Flow<List<ProductFavourites>>

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