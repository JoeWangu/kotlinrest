package com.saddict.djrest.data.sources.remote

import com.saddict.djrest.data.PreferenceDataStore
import com.saddict.djrest.data.sources.ApiRepository
import com.saddict.djrest.network.ProductsApiService
import kotlinx.coroutines.flow.first
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

interface AppApiContainer{
    val productsRepository: ApiRepository
}

object RequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        println("Outgoing request to ${request.url}")
        println("request header is ${request.headers}")
        println("request body is ${request.body}")
        println("request isHttps is ${request.isHttps}")
        println("request method is ${request.method}")
        return chain.proceed(request)
    }
}

class AppApi: AppApiContainer {
    private val baseurl = "https://royaldweeb.pythonanywhere.com/"
    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(RequestInterceptor)
        .build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(JacksonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(baseurl)
        .build()
    private val retrofitService: ProductsApiService by lazy {
        retrofit.create(ProductsApiService::class.java)
    }
    override val productsRepository: ApiRepository by lazy {
        OnlineAppRepository(retrofitService)
    }
}

