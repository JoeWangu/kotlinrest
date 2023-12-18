package com.saddict.djrest.data.manager.usecases

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.saddict.djrest.data.sources.ApiRepository
import com.saddict.djrest.data.sources.local.ProductDatabase
import com.saddict.djrest.model.local.ProductEntity
import com.saddict.djrest.utils.DataMapper.Companion.mapToEntity
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

//@OptIn(ExperimentalPagingApi::class)
//class PagerUseCase @Inject constructor(
//    private val productApi: ApiRepository,
//    private val productDb: ProductDatabase
//): RemoteMediator<Int, ProductEntity>() {
//    private var nextPage = 1
//
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, ProductEntity>
//    ): MediatorResult {
//        val page: Int = when(loadType){
//            LoadType.REFRESH -> nextPage
//            LoadType.PREPEND -> nextPage
//            LoadType.APPEND -> nextPage
//        }
//        try {
//            val response = productApi.getProducts(page).results
//            val entities = response.map { it.mapToEntity() }
//            val endOfPaginationReached = response.isEmpty()
//
//            productDb.withTransaction {
//                if (loadType == LoadType.REFRESH){
//                    productDb.productDao().deleteAllProducts()
//                }
//                val prevKey = if (page > 1) page - 1 else null
//                val nextKey = if (endOfPaginationReached) null else page + 1
//                productDb.productDao().insertAll(entities)
//            }
//            return MediatorResult.Success(endOfPaginationReached)
//        }catch (e: IOException){
//            return MediatorResult.Error(e)
//        }catch (e: HttpException){
//            return MediatorResult.Error(e)
//        }
//    }
//}

@OptIn(ExperimentalPagingApi::class)
class PagerUseCase @Inject constructor(
    private val productApi: ApiRepository,
    private val productDb: ProductDatabase
): RemoteMediator<Int, ProductEntity>() {
    private var nextPage = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProductEntity>
    ): MediatorResult {
        return try {
            when(loadType){
//                LoadType.REFRESH -> nextPage = 1
                LoadType.REFRESH -> {
                    Log.d("Refresh", "Refresh Called")
                    nextPage = 1
                }
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
//                LoadType.APPEND -> Unit
                LoadType.APPEND -> {
                    Log.d("Append", "Append Called")
                    nextPage++
//                    return MediatorResult.Success(endOfPaginationReached = true)
                }
            }
//            delay(2000L)
            val products = productApi.getProducts(
                page = nextPage,
//                pageCount = state.config.pageSize
            )
            productDb.withTransaction {
                if(loadType == LoadType.REFRESH){
                    productDb.productDao().deleteAllProducts()
                }
                val productEntities = products.results.map { it.mapToEntity() }
                productDb.productDao().insertAll(productEntities)
            }
//            if (products.results.isNotEmpty()) {
//                nextPage++
//            }
            MediatorResult.Success(
                endOfPaginationReached = products.results.isEmpty()
//                endOfPaginationReached = true
            )
        }catch (e: IOException){
            MediatorResult.Error(e)
        }catch (e: HttpException){
            MediatorResult.Error(e)
        }
    }
}

//class ExampleRemoteMediator(
//    private val database: ExampleDatabase,
//    private val networkService: ExampleNetworkService
//) : RemoteMediator<Int, ExampleEntity>() {
//
//    override suspend fun load(loadType: LoadType, state: PagingState<Int, ExampleEntity>): MediatorResult {
//        return try {
//            // The network page key you need to load depends on the LoadType:
//            // REFRESH -> Load the first page
//            // PREPEND -> Load the page before the current first page
//            // APPEND -> Load the page after the current last page
//            val pageKey = when (loadType) {
//                LoadType.REFRESH -> null
//                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
//                LoadType.APPEND -> {
//                    val lastItem = state.lastItemOrNull()
//                        ?: return MediatorResult.Success(endOfPaginationReached = true)
//                    lastItem.nextPageKey
//                }
//            }
//
//            // Fetch data from the network
//            val response = networkService.fetchData(pageKey)
//
//            // Store the data in the database
//            database.withTransaction {
//                if (loadType == LoadType.REFRESH) {
//                    database.exampleDao().clearAll()
//                }
//                database.exampleDao().insertAll(response.data)
//            }
//
//            MediatorResult.Success(endOfPaginationReached = response.data.isEmpty())
//        } catch (e: Exception) {
//            MediatorResult.Error(e)
//        }
//    }
//}


/*//@OptIn(ExperimentalPagingApi::class)
//class PagerUseCase @Inject constructor(
//    private val productApi: ApiRepository,
//    private val productDb: ProductDatabase
//): RemoteMediator<Int, ProductEntity>() {
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, ProductEntity>
//    ): MediatorResult {
//        return try {
//            val loadKey = when(loadType){
//                LoadType.REFRESH -> 1
//                LoadType.PREPEND -> return MediatorResult.Success(
//                    endOfPaginationReached = true
//                )
//                LoadType.APPEND -> {
//                    val lastItem = state.lastItemOrNull()
//                    if (lastItem == null){
//                        1
//                    }else{
//                        (lastItem.id / state.config.pageSize) + 1
//                    }
//                }
//            }
//            delay(2000L)
//            val products = productApi.getProducts(
//                page = loadKey,
//                pageCount = state.config.pageSize
//            )
//            productDb.withTransaction {
//                if(loadType == LoadType.REFRESH){
//                    productDb.productDao().deleteAllProducts()
//                }
//                val productEntities = products.results.map { it.mapToEntity() }
//                productDb.productDao().insertAll(productEntities)
//            }
//            MediatorResult.Success(
//                endOfPaginationReached = products.results.isEmpty()
//            )
//        }catch (e: IOException){
//            MediatorResult.Error(e)
//        }catch (e: HttpException){
//            MediatorResult.Error(e)
//        }
//    }
//}*/