package com.saddict.djrest.ui.screens.product

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saddict.djrest.data.manager.usecases.GetAllProductsUseCase
import com.saddict.djrest.data.manager.usecases.GetProductUseCase
import com.saddict.djrest.data.sources.ApiRepository
import com.saddict.djrest.model.local.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import javax.inject.Inject

sealed interface ProductUpdateUiCondition{
    data object Success: ProductUpdateUiCondition
    data object Loading: ProductUpdateUiCondition
    data object Error: ProductUpdateUiCondition
}

@HiltViewModel
class ProductEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
//    context: Context,
//    private val repository: AppDaoRepository
    private val useCase: GetProductUseCase,
//    provide appApi using hilt
    private val repository: ApiRepository
) : ViewModel() {
//    private val apiRepo = AppApi(context).productsRepository
    var productEditUiState by mutableStateOf(ProductEntryUiState())
        private set
    private val productId: Int = checkNotNull(savedStateHandle[ProductEditDestination.productIdArg])
    private val _uiCondition = MutableSharedFlow<ProductUpdateUiCondition>()
    val uiCondition: SharedFlow<ProductUpdateUiCondition> = _uiCondition

    fun updateUiState(entryDetails: EntryDetails) {
        productEditUiState = ProductEntryUiState(
            entryDetails = entryDetails,
            isEntryValid = validateInput(entryDetails)
        )
    }

    init {
        viewModelScope.launch {
            productEditUiState = useCase.getProduct(productId)
                .filterNotNull()
                .first()
                .toProductEditUiState(true)
        }
    }

    suspend fun updateProduct() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    if (validateInput(productEditUiState.entryDetails)) {
                        _uiCondition.emit(ProductUpdateUiCondition.Loading)
                        val updateResponse = repository.updateProduct(
                            id = productId,
                            product = productEditUiState.entryDetails.toPostProducts()
                        )
                        if (updateResponse.isSuccessful){
                            val responseBody = updateResponse.body()
                            Log.d("Success", "$responseBody")
                            _uiCondition.emit(ProductUpdateUiCondition.Success)
                        }else{
                            val errorBody = updateResponse.raw()
                            Log.e("NotSent", "Error: $errorBody")
                            _uiCondition.emit(ProductUpdateUiCondition.Error)
                        }
                    }
                }catch (e: IOException){
                    Log.e("Did not update", "Error: $e")
                }
            }
        }

    }

    private fun validateInput(uiState: EntryDetails = productEditUiState.entryDetails): Boolean {
        return with(uiState) {
            productName.isNotBlank() && modelNumber.isNotBlank()
                    && specifications.isNotBlank() && price.isNotBlank()
        }
    }
}

fun ProductEntity.toEditDetails(): EntryDetails = EntryDetails(
    productName = productName ?: "",
    modelNumber = modelNumber ?: "",
    specifications = specifications ?: "",
    price = price.toString(),
    image = image.toString(),
    category = category.toString(),
    supplier = supplier.toString()
)

fun ProductEntity.toProductEditUiState(isEntryValid: Boolean = false):
        ProductEntryUiState = ProductEntryUiState(
    isEntryValid = isEntryValid,
    entryDetails = this.toEditDetails()
)