package com.saddict.djrest.network

import com.saddict.djrest.model.remote.PostProducts
import com.saddict.djrest.model.remote.Products
import com.saddict.djrest.model.remote.ProductsResult
import com.saddict.djrest.model.remote.User
import com.saddict.djrest.model.remote.UserResponse
import com.saddict.djrest.utils.Constants.LOGIN_URL
import com.saddict.djrest.utils.Constants.PRODUCTS_URL
import com.saddict.djrest.utils.Constants.SINGLE_PRODUCTS_URL
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApiService {
    @GET(PRODUCTS_URL)
    suspend fun getProducts(
        @Query("format") format: String
//                            , @Header("Authorization") token: String
    ): Products

    @GET(SINGLE_PRODUCTS_URL)
    suspend fun getSingleProduct(
        @Path("id") id: Int
//                                 , @Header("Authorization") token: String
    ):
            Call<ProductsResult>
    //    TODO:
    //     1.Create and register users
    //     2.Refresh database after updating or saving

    //    @Headers("Content-Type:application/json")
    @POST(LOGIN_URL)
    suspend fun login(@Body user: User): Response<UserResponse>

    //    @Headers(
//        "Content-Type: application/json",
//        "Authorization: Token your_token_here"
//    )
//    @Headers("Content-Type:application/json")
    @POST(PRODUCTS_URL)
    suspend fun postProducts(@Body body: PostProducts): Response<ProductsResult>

    @PUT(SINGLE_PRODUCTS_URL)
    suspend fun updateProduct(@Path("id") id: Int, @Body body: PostProducts): Response<ProductsResult>
}