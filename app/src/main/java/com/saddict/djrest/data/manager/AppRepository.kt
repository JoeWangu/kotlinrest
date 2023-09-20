package com.saddict.djrest.data.manager

import android.content.Context
import android.util.Log
import com.saddict.djrest.data.sources.ApiRepository
import com.saddict.djrest.data.sources.AppDaoRepository
import com.saddict.djrest.data.sources.local.AppDatabaseContainer
import com.saddict.djrest.data.sources.local.OfflineAppRepository
import com.saddict.djrest.data.sources.local.ProductDatabase
import com.saddict.djrest.data.sources.remote.AppApi
import com.saddict.djrest.model.local.ProductEntity
import com.saddict.djrest.utils.DataMapper.Companion.mapToEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.io.IOException

class AppRepository(
    private val context: Context,
    private val appApi: ApiRepository = AppApi(context).productsRepository,
    private val appDatabase: ProductDatabase = ProductDatabase.getDatabase(context)
) : AppDatabaseContainer {
//    ToDo:
//     1.Add functionality to check whether data from database
//     is outdated and fetch new data if so
//     2.When adding data replace only the outdated parts instead
//     of clearing the entire database and inserting again

//    private val file = File(context.filesDir, "token.txt")
//    val token = KeyStoreManager().decrypt(
//        inputStream = FileInputStream(file)
//    ).decodeToString()

    suspend fun fetchDataAndStore(): Flow<List<ProductEntity>> {
//        val preferenceDataStore = PreferenceDataStore(context)
//        preferenceDataStore.preferenceFlow.collect{ token ->
            val productsInDb = appDatabase.productDao().getAllProducts().first()
            // Check if the database is empty
            if (productsInDb.isEmpty()) {
                // Fetch data from API only if the database is empty
                try {
                    val response = appApi.getProducts()
                    val entities = response.results.map { mapToEntity(it) }
                    entities.let { products ->
                        appDatabase.productDao().insertAll(products)
                    }
                } catch (e: IOException) {
                    // Log the error
                    Log.e("AppRepository", "Error fetching data", e)
                }
            }
//        }
        // Return products from the database
        return appDatabase.productDao().getAllProducts()
    }

//    suspend fun fetchDataAndStore(): Flow<List<ProductEntity>> {
//        val preferenceDataStore = PreferenceDataStore(context)
//        preferenceDataStore.preferenceFlow.collect{ token ->
//            val productsInDb = appDatabase.productDao().getAllProducts().first()
//            // Check if the database is empty
//            if (productsInDb.isEmpty()) {
//                // Fetch data from API only if the database is empty
//                try {
//                    val response = appApi.getProducts(token)
//                    val entities = response.results.map { mapToEntity(it) }
//                    entities.let { products ->
//                        appDatabase.productDao().insertAll(products)
//                    }
//                } catch (e: IOException) {
//                    // Log the error
//                    Log.e("AppRepository", "Error fetching data", e)
//                }
//            }
//        }
//        // Return products from the database
//        return appDatabase.productDao().getAllProducts()
//    }

    override val productsDaoRepository: AppDaoRepository by lazy {
        OfflineAppRepository(appDatabase.productDao())
    }
}

//    suspend fun refreshDatabase() {
//        try {
//            val response = appApi.getProducts()
//            val entities = response.results.map { mapToEntity(it) }
//            entities.let { products ->
//                appDatabase.productDao().insertAll(products)
//            }
//        } catch (e: IOException) {
//            // Log the error
//            Log.e("AppRepository", "Error fetching data", e)
//        }
//    }