package com.saddict.djrest

import com.saddict.djrest.model.remote.PostProducts
import com.saddict.djrest.model.remote.Products
import com.saddict.djrest.model.remote.ProductsResult
import com.saddict.djrest.model.remote.User
import com.saddict.djrest.model.remote.UserResponse
import com.saddict.djrest.network.ProductsApiService
import retrofit2.Call
import retrofit2.Response

class FakeProductsApiService : ProductsApiService {
    override suspend fun getProducts(format: String, token: String): Products {
        TODO("Not yet implemented")
    }

    override suspend fun getSingleProduct(id: Int, token: String): Call<ProductsResult> {
        TODO("Not yet implemented")
    }

    override suspend fun login(user: User): Response<UserResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun postProducts(body: PostProducts): Response<ProductsResult> {
        TODO("Not yet implemented")
    }

    override suspend fun updateProduct(id: Int, body: ProductsResult): Call<ProductsResult> {
        TODO("Not yet implemented")
    }
}