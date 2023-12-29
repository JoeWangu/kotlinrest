package com.saddict.djrest.products.data.sources.remote

import com.saddict.djrest.products.data.sources.ApiRepository
import com.saddict.djrest.products.model.remote.PostProducts
import com.saddict.djrest.products.model.remote.Products
import com.saddict.djrest.products.model.remote.ProductsResult
import com.saddict.djrest.products.model.remote.RegisterUser
import com.saddict.djrest.products.model.remote.RegisterUserResponse
import com.saddict.djrest.products.model.remote.User
import com.saddict.djrest.products.model.remote.UserResponse
import com.saddict.djrest.products.network.ProductsApiService
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class OnlineAppRepository @Inject constructor(
    private val api: ProductsApiService
) : ApiRepository {
    override suspend fun getProducts(page: Int): Products =
        api.getProducts(format = "json", page = page)

    //    , pageCount = pageCount
    override suspend fun postProducts(products: PostProducts): Response<ProductsResult> =
        api.postProducts(products)

    override suspend fun getSingleProduct(id: Int): Call<ProductsResult> =
        api.getSingleProduct(id = id)

    override suspend fun updateProduct(id: Int, product: PostProducts)
            : Response<ProductsResult> = api.updateProduct(id = id, body = product)

    override suspend fun login(user: User): Response<UserResponse> =
        api.login(user)

    override suspend fun register(user: RegisterUser)
            : Response<RegisterUserResponse> = api.register(user)
}