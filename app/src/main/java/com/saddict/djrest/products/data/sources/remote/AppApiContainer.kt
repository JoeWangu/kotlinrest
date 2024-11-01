package com.saddict.djrest.products.data.sources.remote

import android.content.Context
import com.saddict.djrest.products.data.manager.PreferenceDataStore
import com.saddict.djrest.utils.Constants.CREATE_USER_URL
import com.saddict.djrest.utils.Constants.LOGIN_URL
import okhttp3.Interceptor
import okhttp3.Response

//interface AppApiContainer {
//    val productsRepository: ApiRepository
//}

class RequestInterceptor(context: Context) : Interceptor {
    private val preferenceDataStore = PreferenceDataStore(context)

    //    private val token = preferenceDataStore.preferenceFlow.map { it }
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val token = preferenceDataStore.getToken()
        println("Outgoing request to ${request.url}")
        println("Token is $token")
//         Check the request's properties and decide whether to modify it
        return if (
            !request.url.encodedPath.contains(LOGIN_URL)
            && !request.url.encodedPath.contains(CREATE_USER_URL)
        ) {
            // Add headers
            val requestBuild = request.newBuilder()
                .addHeader("Authorization", "Token $token")
                .header("Content-Type", "application/json")
                .build()
            println("request header is ${requestBuild.headers}")
            chain.proceed(requestBuild)
        } else {
            val requestBuild = request.newBuilder().build()
            chain.proceed(requestBuild)
        }
    }
}

//class AppApi(context: Context) : AppApiContainer {
//    private val baseurl = BASE_URL
//    private val bodyInterceptor =
//        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
////    private val headerInterceptor =
////        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
//    private val okHttpClient = OkHttpClient()
//        .newBuilder()
//        .addInterceptor(RequestInterceptor(context))
//        .addInterceptor(bodyInterceptor)
////        .addInterceptor(headerInterceptor)
//        .build()
//    private val retrofit = Retrofit.Builder()
//        .addConverterFactory(JacksonConverterFactory.create())
//        .client(okHttpClient)
//        .baseUrl(baseurl)
//        .build()
//    private val retrofitService: ProductsApiService by lazy {
//        retrofit.create(ProductsApiService::class.java)
//    }
//    override val productsRepository: ApiRepository by lazy {
//        OnlineAppRepository(retrofitService)
//    }
//}

//object RequestInterceptor: Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request()
//        println("Outgoing request to ${request.url}")
//        println("request header is ${request.headers}")
//        println("request body is ${request.body}")
//        println("request isHttps is ${request.isHttps}")
//        println("request method is ${request.method}")
//        // Add headers
//        val requestBuild = request.newBuilder()
////            .addHeader("Authorization", "")
//            .header("Content-Type", "application/json")
//            .build()
//        println("request header is ${requestBuild.headers}")
//        return chain.proceed(requestBuild)
//    }
//}