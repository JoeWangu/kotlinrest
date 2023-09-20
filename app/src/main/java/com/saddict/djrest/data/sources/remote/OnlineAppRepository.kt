package com.saddict.djrest.data.sources.remote

import com.saddict.djrest.data.sources.ApiRepository
import com.saddict.djrest.model.remote.PostProducts
import com.saddict.djrest.model.remote.Products
import com.saddict.djrest.model.remote.ProductsResult
import com.saddict.djrest.model.remote.User
import com.saddict.djrest.model.remote.UserResponse
import com.saddict.djrest.network.ProductsApiService
import retrofit2.Call
import retrofit2.Response

class OnlineAppRepository(private val productsApiService: ProductsApiService) :
    ApiRepository {
    override suspend fun getProducts(): Products =
        productsApiService.getProducts("json")
    override suspend fun postProducts(products: PostProducts): Response<Products> =
        productsApiService.postProducts(products)
    override suspend fun getSingleProduct(id: Int): Call<ProductsResult> =
        productsApiService.getSingleProduct(id = id)
    override suspend fun updateProduct(id: Int, product: ProductsResult): Call<Products> =
        productsApiService.updateProduct(id = id, body = product)
    override suspend fun login(user: User): Response<UserResponse> =
        productsApiService.login(user)
}