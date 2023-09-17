package com.saddict.djrest.data.sources

import com.saddict.djrest.model.local.ProductEntity
import com.saddict.djrest.model.remote.PostProducts
import com.saddict.djrest.model.remote.Products
import com.saddict.djrest.model.remote.ProductsResult
import com.saddict.djrest.model.remote.User
import com.saddict.djrest.model.remote.UserResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response

interface ApiRepository {
    suspend fun getProducts(tokenPass: String): Products
    suspend fun postProducts(products: PostProducts): Response<ProductsResult>
    suspend fun getSingleProduct(id: Int, tokenPass: String): Call<ProductsResult>
    suspend fun updateProduct(id: Int, product: ProductsResult): Call<ProductsResult>
    suspend fun login(user: User): Response<UserResponse>
}
/**
 * Repository that provides insert, update, delete, and retrieve of [Products] from a given data source.
 */
interface AppDaoRepository {
//    fun getAllProductsResponseStream(): Flow<Products>
//    suspend fun insertAll(products: Products)
//    suspend fun insertProductResponse(productResponse: Products)
//    suspend fun deleteProductResponse(productResponse: Products)
//    suspend fun updateProductResponse(productResponse: Products)

    suspend fun insertAll(products: List<ProductEntity>)
    fun getAllProducts(): Flow<List<ProductEntity>>
    fun getProduct(id: Int): Flow<ProductEntity?>

//    suspend fun insertProduct(product: List<ProductsResult>)
//    suspend fun deleteProduct(product: ProductsResult)
//    suspend fun updateProduct(product: ProductsResult)
}

//class MyRepository(private val myDao: MyDao) {
//    suspend fun insertData(networkData: NetworkDataClass) {
//        val entityData = EntityDataClass(networkData.id, networkData.image)
//        myDao.insert(entityData)
//    }