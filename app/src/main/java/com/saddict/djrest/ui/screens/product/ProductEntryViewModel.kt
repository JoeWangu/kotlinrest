package com.saddict.djrest.ui.screens.product

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fasterxml.jackson.databind.ObjectMapper
import com.saddict.djrest.data.AppRepository
import com.saddict.djrest.data.sources.AppDaoRepository
import com.saddict.djrest.data.sources.remote.AppApi
import com.saddict.djrest.model.remote.ImageArrayResults
import com.saddict.djrest.model.remote.PostProducts
import com.saddict.djrest.model.remote.ProductsResult
import okio.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductEntryViewModel(
//    private val repository: AppDaoRepository,
//    context: Context
) : ViewModel() {
//    private val apiRepo = AppRepository(context)
//    private val apiRepo = AppNetworkRepository()
    private val apiRepo = AppApi().productsRepository
    var productEntryUiState by mutableStateOf(ProductEntryUiState())
        private set

    fun updateUiState(entryDetails: EntryDetails) {
        productEntryUiState =
            ProductEntryUiState(
                entryDetails = entryDetails,
                isEntryValid = validateInput(entryDetails)
            )
    }

    suspend fun saveProduct() {
        if (validateInput()) {
            val response = apiRepo.postProducts(productEntryUiState.entryDetails.toPostProducts())
            if (response.isSuccessful){
                val responseBody = response.body()
                Log.d("Success", "$responseBody")
            }else{
                val errorBody = response.errorBody()?.toString()
                Log.e("NotSent", "Error: $errorBody")
            }
        }
    }

    private fun validateInput(uiState: EntryDetails = productEntryUiState.entryDetails): Boolean {
        return with(uiState) {
            productName.isNotBlank() && modelNumber.isNotBlank() && specifications.isNotBlank() && price.isNotBlank()
        }
    }
}

data class ProductEntryUiState(
    val entryDetails: EntryDetails = EntryDetails(),
    val isEntryValid: Boolean = false
)

data class EntryDetails(
    val productName: String = "",
    val modelNumber: String = "",
    val specifications: String = "",
    val price: String = ""
)

fun ProductsResult.toEntryDetails(): EntryDetails = EntryDetails(
    productName = productName ?: "",
    modelNumber = modelNumber ?: "",
    specifications = specifications ?: "",
    price = price.toString()
)

fun EntryDetails.toPostProducts(): PostProducts = PostProducts(
    productName = productName,
    modelNumber = modelNumber,
    specifications = specifications,
    price = price.toIntOrNull() ?: 0,
    image = 1,
    category = 1,
    supplier = 1
)

fun EntryDetails.toProductsResult(): ProductsResult = ProductsResult(
    id = 0,
    productName = productName,
    modelNumber = modelNumber,
    specifications = specifications,
    price = price.toDoubleOrNull() ?: 0.0,
    image = 1,
    supplier = 1,
    imageDetail = ImageArrayResults(id = 1, image = null),
    category = 1
)

//fun ProductsResult.toProductEntryUiState(isEntryValid: Boolean = false):
//        ProductEntryUiState = ProductEntryUiState(
//    isEntryValid = isEntryValid,
//    entryDetails = this.toEntryDetails()
//)