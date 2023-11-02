package com.saddict.djrest.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.saddict.djrest.data.sources.ApiRepository
import com.saddict.djrest.data.sources.local.ProductDatabase
import com.saddict.djrest.model.local.ProductEntity
import com.saddict.djrest.model.remote.Products
import com.saddict.djrest.model.remote.ProductsResult
import com.saddict.djrest.utils.DataMapper.Companion.mapToResults
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import javax.inject.Inject

//sealed interface ProductsUiState {
//    data class Success(val products: List<ProductsResult> = listOf()) : ProductsUiState
//
//    object Error : ProductsUiState
//    object Loading : ProductsUiState
//}
//sealed interface ProductsUiState {
//    data class Success(val products: Products) : ProductsUiState
//    data object Error : ProductsUiState
//    data object Loading : ProductsUiState
//}

@HiltViewModel
class HomeViewModel @Inject constructor(
//    repository: AppDaoRepository,
//    context: Context,
//    private val repo: AppRepository,
//    private val productsApiService: ProductsApiService
//    private val repository: ApiRepository,
    pager: Pager<Int, ProductEntity>,
    private val productDb: ProductDatabase
) : ViewModel() {
//    var uiState: ProductsUiState by mutableStateOf(ProductsUiState.Loading)
//        private set

    val productPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.mapToResults() }
        }
        .cachedIn(viewModelScope)

    fun deleteAll(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                productDb.productDao().deleteAllProducts()
            }
        }
    }
//    init {
//        getProducts()
//    }

//    fun getProducts() {
//        viewModelScope.launch {
////            withContext(Dispatchers.IO) {
//                uiState = ProductsUiState.Loading
//                uiState = try {
//                    ProductsUiState.Success(repository.getProducts(1, pageCount = 5))
//                } catch (e: IOException) {
//                    ProductsUiState.Error
//                }
////            }
//        }
//    }

//    private val repo = AppRepository(context = context)
//    val productsUiState: StateFlow<ProductsUiState> = repository.getAllProducts().map {
//        ProductsUiState.Success(it)
//    }.stateIn(
//        scope = viewModelScope,
//        initialValue = ProductsUiState.Loading,
//        started = SharingStarted.WhileSubscribed(5_000L)
//    )

//    private fun getPageProducts() = Pager(
//        config = PagingConfig(
//            pageSize = 5
//        ),
//        pagingSourceFactory = { ProductPagingSource(productsApiService) }
//    ).flow
//
//    fun productFlow(): Flow<PagingData<ProductsResult>> =
//        getPageProducts().cachedIn(viewModelScope)

//    fun productFlow(): Flow<PagingData<ProductsResult>> =
//        repo.getPageProducts().cachedIn(viewModelScope)
    //    var productsUiState: ProductsUiState by mutableStateOf(ProductsUiState.Loading)
//    private val repo = AppRepository(context)
//    private var pageNumber = 1

//    init {
//        runRepo()
////        getProducts()
//    }

//    private fun runRepo() {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                repo.fetchDataAndStore(1)
//            }
//        }
//    }

//    fun getMoreData() {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                if (pageNumber in 1..3) {
//                    repo.fetchDataAndStore(pageNumber)
//                    pageNumber++
//                }
//            }
//        }
//    }

//    fun getProducts() {
//        viewModelScope.launch {
//            try {
//                    withContext(Dispatchers.IO) {
//                        repo.refreshDatabase()
//                    }
//            } catch (e: IOException) {
//                withContext(Dispatchers.IO) {
//                    Log.e("ERROR","IO Error")
//                }
//            } catch (e: HttpException) {
//                withContext(Dispatchers.IO) {
//                    Log.e("ERROR","HTTP Error")
//                }
//            }
//        }
//    }

//    fun shareProduct(context: Context, summary: String) {
//        val intent = Intent(Intent.ACTION_SEND).apply {
//            type = "text/plain"
//            putExtra(Intent.EXTRA_TEXT, summary)
//        }
//        context.startActivity(
//            Intent.createChooser(
//                intent,
//                context.getString(R.string.app_name)
//            )
//        )
//    }
}

//    fun getProducts() {
//        viewModelScope.launch {
//            productsUiState = ProductsUiState.Loading
//            try {
//                repository.getAllProducts().collect { products ->
//                    withContext(Dispatchers.IO) {
//                        productsUiState = ProductsUiState.Success(products)
//                    }
//                }
//            } catch (e: IOException) {
//                withContext(Dispatchers.IO) {
//                    productsUiState = ProductsUiState.Error
//                }
//            } catch (e: HttpException) {
//                withContext(Dispatchers.IO) {
//                    productsUiState = ProductsUiState.Error
//                }
//            }
//        }
//    }

//    fun refreshDb() {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                repo.refreshDatabase()
//            }
//        }
//    }
//others//

//data class HomeUiState(val itemList: List<ProductEntity?> = listOf())

//class ProductsViewModel(private val productsRepository: AppDaoRepository) : ViewModel() {
//    //    var productsUiState: ProductsUiState by mutableStateOf(ProductsUiState.Loading)
//    val homeUiState: StateFlow<ProductUiState> =
//        productsRepository.getAllProducts().map { ProductUiState(it) }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(5_000L),
//                initialValue = ProductUiState()
//            )
//    init {
//        getProducts()
//    }

//    fun getProducts() {
//        viewModelScope.launch {
//            productsUiState = ProductsUiState.Loading
//            productsUiState = try {
//                ProductsUiState.Success(productsRepository.getAllProducts())
//            } catch (e: IOException) {
//                ProductsUiState.Error
////                ProductsUiState.Success(productsRepository.getAllProductsResponseStream())
//            } catch (e: HttpException) {
//                ProductsUiState.Error
////                ProductsUiState.Success(productsRepository.getAllProductsResponseStream())
//            }
//        }
//    }
//}

//                repository.getAllProducts().collect { products ->
//                    withContext(Dispatchers.IO) {
//                        productsUiState = ProductsUiState.Success(products)
//                    }
//                }


//val homeUiState: StateFlow<ProductsUiState> =
//    repository.getAllProducts().map { ProductsUiState.Success(it) }
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5_000L),
//            initialValue = ProductsUiState.Success()
//        )
////                if (homeUiState.value != null){
//productsUiState = ProductsUiState.Success()
////                }


//    companion object {
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val application = (this[APPLICATION_KEY] as MyApp)
//                val productsRepository = application.container.productsDaoRepository
//                ProductsViewModel(
//                    repository = productsRepository,
//                    context = application.applicationContext
//                )
//            }
//        }
//    }
