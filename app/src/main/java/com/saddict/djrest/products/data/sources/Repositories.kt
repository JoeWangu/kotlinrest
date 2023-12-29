package com.saddict.djrest.products.data.sources

import com.saddict.djrest.products.model.local.ProductEntity
import com.saddict.djrest.products.model.local.ProductFavourites
import com.saddict.djrest.products.model.remote.PostProducts
import com.saddict.djrest.products.model.remote.Products
import com.saddict.djrest.products.model.remote.ProductsResult
import com.saddict.djrest.products.model.remote.RegisterUser
import com.saddict.djrest.products.model.remote.RegisterUserResponse
import com.saddict.djrest.products.model.remote.User
import com.saddict.djrest.products.model.remote.UserResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response

interface ApiRepository {
    suspend fun getProducts(page: Int): Products

    //    , pageCount: Int
    suspend fun getSingleProduct(id: Int): Call<ProductsResult>
    suspend fun postProducts(products: PostProducts): Response<ProductsResult>
    suspend fun updateProduct(id: Int, product: PostProducts): Response<ProductsResult>
    suspend fun login(user: User): Response<UserResponse>
    suspend fun register(user: RegisterUser): Response<RegisterUserResponse>
}

/**
 * Repository that provides insert, update, delete, and retrieve of [Products] from a given data source.
 */
//AppDaoRepository
interface LocalDataSource {
//    fun getAllProductsResponseStream(): Flow<Products>
//    suspend fun insertAll(products: Products)
//    suspend fun insertProductResponse(productResponse: Products)
//    suspend fun deleteProductResponse(productResponse: Products)
//    suspend fun updateProductResponse(productResponse: Products)

    suspend fun insertAll(products: List<ProductEntity>)
    fun getAllProducts(): Flow<List<ProductEntity>>
    fun getProduct(id: Int): Flow<ProductEntity?>
    suspend fun insertFavourite(favourite: ProductFavourites)
    fun getAllFavourites(): Flow<List<ProductFavourites>>
//    fun loadAllPaged(): PagingSource<Int, ProductEntity>
//    fun customLoadAllPaged(): CustomPagingSource

//    suspend fun insertProduct(product: List<ProductsResult>)
//    suspend fun deleteProduct(product: ProductsResult)
//    suspend fun updateProduct(product: ProductsResult)
}

//class MyRepository(private val myDao: MyDao) {
//    suspend fun insertData(networkData: NetworkDataClass) {
//        val entityData = EntityDataClass(networkData.id, networkData.image)
//        myDao.insert(entityData)
//    }