package com.saddict.djrest.ui.screens

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.saddict.djrest.ProductsApplication
import com.saddict.djrest.ui.screens.home.ProductsViewModel
import com.saddict.djrest.ui.screens.login.LoginViewModel
import com.saddict.djrest.ui.screens.product.ProductDetailsViewModel
import com.saddict.djrest.ui.screens.product.ProductEditViewModel
import com.saddict.djrest.ui.screens.product.ProductEntryViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer HomeViewModel
        initializer {
            ProductsViewModel(
                productApplication().container.productsDaoRepository,
                context = productApplication().applicationContext
            )
        }
        initializer {
            ProductDetailsViewModel(
                this.createSavedStateHandle(),
                productApplication().container.productsDaoRepository
            )
        }
        initializer {
            ProductEntryViewModel(
                context = productApplication().applicationContext
            )
        }
        initializer {
            ProductEditViewModel(
                this.createSavedStateHandle(),
                context = productApplication().applicationContext,
                productApplication().container.productsDaoRepository
            )
        }
        initializer {
            LoginViewModel(
                context = productApplication().applicationContext
//                preferenceDataStore = PreferenceDataStore(productApplication().applicationContext)
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [ProductsApplication].
 */
fun CreationExtras.productApplication(): ProductsApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ProductsApplication)
