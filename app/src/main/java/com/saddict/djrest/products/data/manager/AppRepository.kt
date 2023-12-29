package com.saddict.djrest.products.data.manager

//import kotlinx.coroutines.flow.first
//import android.content.Context
//import com.saddict.djrest.data.sources.LocalDataSource
//import com.saddict.djrest.data.sources.local.ProductDatabase
//import com.saddict.djrest.data.sources.local.RoomLocalDataSource
//import com.saddict.djrest.model.local.ProductEntity
//import kotlinx.coroutines.flow.Flow
//import javax.inject.Inject

//class AppRepository @Inject constructor(
////    private val context: Context,
////    private val appApi: ApiRepository = AppApi(context).productsRepository,
////    private val appDatabase: ProductDatabase = ProductDatabase.getDatabase(context),
////    private val productsApiService: ProductsApiService
////    private val productDao: ProductDao
//    private val localDataSource: LocalDataSource
//) {
//    suspend fun insertAll(productEntity: List<ProductEntity>){
//        localDataSource.insertAll(productEntity)
//    }
//    fun getAllProducts(): Flow<List<ProductEntity>>{
//        return localDataSource.getAllProducts()
//    }
//    fun getProduct(id: Int) : Flow<ProductEntity?> {
//        return localDataSource.getProduct(id)
//    }

//    ToDo:
//     1.Add functionality to check whether data from database
//     is outdated and fetch new data if so
//     2.When adding data replace only the outdated parts instead
//     of clearing the entire database and inserting again

//    fun getPageProducts() = Pager(
//        config = PagingConfig(
//            pageSize = 5
//        ),
//        pagingSourceFactory = { ProductPagingSource(productsApiService) }
//    ).flow

//    private val file = File(context.filesDir, "token.txt")
//    val token = KeyStoreManager().decrypt(
//        inputStream = FileInputStream(file)
//    ).decodeToString()

//    suspend fun fetchDataAndStore(page: Int): Flow<List<ProductEntity>> {
////            val productsInDb = appDatabase.productDao().getAllProducts().first()
//            // Check if the database is empty
////            if (productsInDb.isEmpty()) {
//                // Fetch data from API only if the database is empty
//                try {
//                    val response = appApi.getProducts(page)
//                    val entities = response.results.map { mapToEntity(it) }
//                    entities.let { products ->
//                        appDatabase.productDao().insertAll(products)
//                    }
//                } catch (e: IOException) {
//                    // Log the error
//                    Log.e("AppRepository", "Error fetching data", e)
//                    context.toastUtil("Error fetching latest data")
//                    delay(1_000L)
//                    context.toastUtil("No internet ")
//                }
////            }
//        // Return products from the database
//        return appDatabase.productDao().getAllProducts()
//    }

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

//    override val productsDaoRepository: AppDaoRepository by lazy {
//        OfflineAppRepository(appDatabase.productDao())
//    }
//    override val productsDaoRepository: LocalDataSource by lazy {
//    RoomLocalDataSource(localDataSource)
//    }
//}

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