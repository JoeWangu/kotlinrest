package com.saddict.djrest.ui.screens.product

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saddict.djrest.data.sources.AppDaoRepository
import com.saddict.djrest.data.sources.remote.AppApi
import com.saddict.djrest.model.local.ProductEntity
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProductEditViewModel(
    savedStateHandle: SavedStateHandle,
    context: Context,
    private val repository: AppDaoRepository
) : ViewModel() {
    //    private val repo = AppRepository(context = context)
    private val apiRepo = AppApi(context).productsRepository
    var productEditUiState by mutableStateOf(ProductEntryUiState())
        private set
    private val productId: Int = checkNotNull(savedStateHandle[ProductEditDestination.productIdArg])

    fun updateUiState(entryDetails: EntryDetails) {
        productEditUiState = ProductEntryUiState(
            entryDetails = entryDetails,
            isEntryValid = validateInput(entryDetails)
        )
    }

    init {
        viewModelScope.launch {
            productEditUiState = repository.getProduct(productId)
                .filterNotNull()
                .first()
                .toProductEditUiState(true)
        }
    }

    suspend fun updateProduct() {
        if (validateInput(productEditUiState.entryDetails)) {
            apiRepo.updateProduct(
                id = productId,
                product = productEditUiState.entryDetails.toProductsResult()
            )
//            repository.updateItem(itemUiState.itemDetails.toItem())
        }
    }

    private fun validateInput(uiState: EntryDetails = productEditUiState.entryDetails): Boolean {
        return with(uiState) {
            productName.isNotBlank() && modelNumber.isNotBlank() && specifications.isNotBlank() && price.isNotBlank()
        }
    }
}

fun ProductEntity.toEditDetails(): EntryDetails = EntryDetails(
    productName = productName ?: "",
    modelNumber = modelNumber ?: "",
    specifications = specifications ?: "",
    price = price.toString()
)

fun ProductEntity.toProductEditUiState(isEntryValid: Boolean = false):
        ProductEntryUiState = ProductEntryUiState(
    isEntryValid = isEntryValid,
    entryDetails = this.toEditDetails()
)