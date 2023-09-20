package com.saddict.djrest.ui.screens.home

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saddict.djrest.R
import com.saddict.djrest.data.manager.AppRepository
import com.saddict.djrest.data.sources.AppDaoRepository
import com.saddict.djrest.model.local.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed interface ProductsUiState {
    data class Success(val products: List<ProductEntity?> = listOf()) : ProductsUiState
//    data object Error : ProductsUiState
    data object Loading : ProductsUiState
}

class ProductsViewModel(repository: AppDaoRepository, context: Context) : ViewModel() {
    //    var productsUiState: ProductsUiState by mutableStateOf(ProductsUiState.Loading)
    private val repo = AppRepository(context)

    init {
        runRepo()
//        getProducts()
    }

    private fun runRepo() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repo.fetchDataAndStore()
            }
        }
    }

    val productsUiState: StateFlow<ProductsUiState> = repository.getAllProducts().map {
        ProductsUiState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = ProductsUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000L)
    )

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

    fun shareProduct(context: Context, summary: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, summary)
        }
        context.startActivity(
            Intent.createChooser(
                intent,
                context.getString(R.string.app_name)
            )
        )
    }
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
//                val application = (this[APPLICATION_KEY] as ProductsApplication)
//                val productsRepository = application.container.productsDaoRepository
//                ProductsViewModel(
//                    repository = productsRepository,
//                    context = application.applicationContext
//                )
//            }
//        }
//    }