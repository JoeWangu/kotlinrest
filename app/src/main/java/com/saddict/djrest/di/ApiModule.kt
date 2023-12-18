package com.saddict.djrest.di

import android.content.Context
import com.saddict.djrest.data.sources.remote.RequestInterceptor
import com.saddict.djrest.network.ProductsApiService
import com.saddict.djrest.prop.Prop
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideMyApi(@ApplicationContext context: Context): ProductsApiService {
        val bodyInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(RequestInterceptor(context))
            .addInterceptor(bodyInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Prop.BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ProductsApiService::class.java)
    }
}

//    @Provides
//    @Singleton
//    fun provideMyRepository(api: ProductsApiService): ApiRepository{
//        return OnlineAppRepository(api)
//    }

//    Providing Context
//    @Provides
//    @Singleton
//    fun provideMyRepository(api: ProductsApiService, app: Application): ApiRepository {
//        return OnlineAppRepository(api, app)
//    }
