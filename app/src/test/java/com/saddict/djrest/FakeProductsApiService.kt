package com.saddict.djrest

import com.saddict.djrest.model.remote.Products
import com.saddict.djrest.model.remote.ProductsResult
import com.saddict.djrest.network.ProductsApiService
import retrofit2.Call

class FakeProductsApiService : ProductsApiService {
    override suspend fun getProducts(format: String): Products {
        return FakeDataSource.products
    }
    override suspend fun getSingleProduct(id: Int): Call<ProductsResult> { TODO("Not yet implemented") }
    override suspend fun postProducts(body: ProductsResult): Call<ProductsResult> { TODO("Not yet implemented") }
    override suspend fun updateProduct(id: Int, body: ProductsResult): Call<ProductsResult> { TODO("Not yet implemented") }
}