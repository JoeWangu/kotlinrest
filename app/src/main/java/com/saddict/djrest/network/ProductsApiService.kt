package com.saddict.djrest.network

import com.saddict.djrest.model.remote.PostProducts
import com.saddict.djrest.model.remote.Products
import com.saddict.djrest.model.remote.ProductsResult
import com.saddict.djrest.model.remote.User
import com.saddict.djrest.model.remote.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
//import retrofit2.Response
//import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApiService {
    @GET("inventory/api/products/")
    suspend fun getProducts(@Query("format")format: String, @Header("Authorization") token: String): Products

    @GET("inventory/api/products/{id}/")
    suspend fun getSingleProduct(@Path("id") id: Int, @Header("Authorization") token: String) :
            Call<ProductsResult>
    //    TODO:
    //     1.Complete post requests
    //     2.Add put requests
    //     3.Add authentication

    @Headers("Content-Type:application/json")
    @POST("login-api/")
    suspend fun login(@Body user: User) : Response<UserResponse>

//    @Headers(
//        "Content-Type: application/json",
//        "Authorization: Token your_token_here"
//    )
    @Headers("Content-Type:application/json")
    @POST("inventory/api/products/")
    suspend fun postProducts(@Body body: PostProducts): Response<ProductsResult>

    @PUT("inventory/api/products/{id}/")
    suspend fun updateProduct(@Path("id") id: Int, @Body body: ProductsResult): Call<ProductsResult>
}