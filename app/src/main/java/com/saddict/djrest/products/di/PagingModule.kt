package com.saddict.djrest.products.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.saddict.djrest.products.data.manager.CustomPagingSource
import com.saddict.djrest.products.data.sources.ApiRepository
import com.saddict.djrest.products.data.sources.local.ProductDatabase
import com.saddict.djrest.products.model.local.ProductEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagingModule {
    @Provides
    @Singleton
    fun providePager(
        productApi: ApiRepository,
        productDb: ProductDatabase
    ): Pager<Int, ProductEntity> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 1, enablePlaceholders = false),
//            remoteMediator = PagerUseCase(
//                productApi = productApi,
//                productDb = productDb
//            ),
            pagingSourceFactory = {
                CustomPagingSource(productApi = productApi, productDb = productDb)
//                productDb.productDao().pagingSource()
            }
        )
    }
}

//@Module
//@InstallIn(SingletonComponent::class)
//object PagingModule {
//    @OptIn(ExperimentalPagingApi::class)
//    @Provides
//    @Singleton
//    fun providePager(
//        productDb: ProductDatabase
//    ): Pager<Int, ProductEntity> {
//        return Pager(
//            config = PagingConfig(pageSize = 10, prefetchDistance = 1),
//            pagingSourceFactory = { productDb.productDao().pagingSource() }
//        )
//    }
//}


//val pager = Pager(PagingConfig(pageSize = 5)) {
//    object : PagingSource<Int, ProductsResult>() {
//        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductsResult> {
//            return try {
//                val nextPageNumber = params.key ?: 1
//                val response = getProducts(nextPageNumber) // Your function to get products
//                LoadResult.Page(
//                    data = response,
//                    prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null,
//                    nextKey = nextPageNumber + 1
//                )
//            } catch (e: Exception) {
//                LoadResult.Error(e)
//            }
//        }
//    }
//}.flow.cachedIn(viewModelScope).collectLatest { pagingData ->
//    _products.value = pagingData
//}
//
//val products: LiveData<PagingData<ProductsResult>> = _products
