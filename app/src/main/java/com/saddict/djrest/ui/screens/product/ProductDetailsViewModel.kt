package com.saddict.djrest.ui.screens.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saddict.djrest.data.sources.AppDaoRepository
import com.saddict.djrest.model.local.ProductEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class ProductDetails(
    val id: Int = 0,
    val productName: String? = "",
    val modelNumber: String? = "",
    val specifications: String? = "",
    val price: String? = "",
    val image: Int? = 0,
    val imageUrl: String? = "",
    val category: String? = "",
    val supplier: String? = "",
)

data class ProductDetailsUiState(
    val productDetails: ProductDetails = ProductDetails()
)

class ProductDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: AppDaoRepository
): ViewModel() {
    private val productId: Int = checkNotNull(savedStateHandle[ProductDetailsDestination.productIdArg])
    val uiState: StateFlow<ProductDetailsUiState> =
        repository.getProduct(productId)
            .filterNotNull()
            .map { ProductDetailsUiState(productDetails = it.toProductDetails()) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ProductDetailsUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

fun ProductEntity.toProductDetails(): ProductDetails = ProductDetails(
    id = id,
    productName = productName,
    modelNumber = modelNumber,
    specifications = specifications,
    price = price.toString(),
    image = image,
    imageUrl = imageDetail,
    category = category.toString(),
    supplier = supplier.toString()
)

fun ProductDetails.toProductEntity(): ProductEntity = ProductEntity(
    id = id,
    productName = productName,
    modelNumber = modelNumber,
    specifications = specifications,
    price = price?.toDoubleOrNull() ?: 0.0,
    image = image,
    category = category?.toIntOrNull() ?: 0,
    supplier = supplier?.toIntOrNull() ?: 0,
    imageDetail = imageUrl
)