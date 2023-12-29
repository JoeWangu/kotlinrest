package com.saddict.djrest.products.data.manager.usecases

//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import androidx.room.withTransaction
//import com.saddict.djrest.data.sources.ApiRepository
//import com.saddict.djrest.data.sources.local.ProductDatabase
//import com.saddict.djrest.model.local.ProductEntity
//import com.saddict.djrest.utils.DataMapper.Companion.mapToEntity
//import retrofit2.HttpException
//import java.io.IOException
//import javax.inject.Inject
//
//
//@OptIn(ExperimentalPagingApi::class)
//class PagerUseCase @Inject constructor(
//    private val productApi: ApiRepository,
//    private val productDb: ProductDatabase
//) : RemoteMediator<Int, ProductEntity>() {
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, ProductEntity>
//    ): MediatorResult {
//        try {
//            val page = when (loadType) {
//                LoadType.REFRESH -> 1
//                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
//                LoadType.APPEND -> {
//                    val lastItem = state.lastItemOrNull()
//                    lastItem?.page ?: return MediatorResult.Success(endOfPaginationReached = true)
//                }
//            }
//            val response = productApi.getProducts(page)
//            val data = response.results.map { it.mapToEntity() }
//
//            productDb.withTransaction {
//                if (loadType == LoadType.REFRESH) {
//                    productDb.productDao().deleteAllProducts()
//                }
//                productDb.productDao().insertAll(data)
//            }
//            val endOfPaginationReached = data.isEmpty()
//            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
//        } catch (e: IOException) {
//            return MediatorResult.Error(e)
//        } catch (e: HttpException) {
//            return MediatorResult.Error(e)
//        }
//    }
//
//    override suspend fun initialize(): InitializeAction {
//        return InitializeAction.LAUNCH_INITIAL_REFRESH
//    }
//}


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
//        return try {
//            when(loadType){
////                LoadType.REFRESH -> nextPage = 1
//                LoadType.REFRESH -> {
//                    Log.d("Refresh", "Refresh Called")
//                    nextPage = 1
//                }
//                LoadType.PREPEND -> return MediatorResult.Success(
//                    endOfPaginationReached = true
//                )
////                LoadType.APPEND -> Unit
//                LoadType.APPEND -> {
//                    Log.d("Append", "Append Called")
//                    nextPage++
////                    return MediatorResult.Success(endOfPaginationReached = true)
//                }
//            }
////            delay(2000L)
//            val products = productApi.getProducts(
//                page = nextPage,
////                pageCount = state.config.pageSize
//            )
//            productDb.withTransaction {
//                if(loadType == LoadType.REFRESH){
//                    productDb.productDao().deleteAllProducts()
//                }
//                val productEntities = products.results.map { it.mapToEntity() }
//                productDb.productDao().insertAll(productEntities)
//            }
////            if (products.results.isNotEmpty()) {
////                nextPage++
////            }
//            MediatorResult.Success(
//                endOfPaginationReached = products.results.isEmpty()
////                endOfPaginationReached = true
//            )
//        }catch (e: IOException){
//            MediatorResult.Error(e)
//        }catch (e: HttpException){
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