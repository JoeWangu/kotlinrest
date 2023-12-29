package com.saddict.djrest.products.ui.screens

//import android.app.Application
//import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
//import androidx.lifecycle.createSavedStateHandle
//import androidx.lifecycle.viewmodel.CreationExtras
//import androidx.lifecycle.viewmodel.initializer
//import androidx.lifecycle.viewmodel.viewModelFactory
//import com.saddict.djrest.MyApp
//import com.saddict.djrest.ui.screens.product.ProductDetailsViewModel
//import com.saddict.djrest.ui.screens.product.ProductEditViewModel
//import com.saddict.djrest.ui.screens.product.ProductEntryViewModel
//import com.saddict.djrest.ui.screens.registration.LoginViewModel
//import com.saddict.djrest.ui.screens.registration.RegisterViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
//object AppViewModelProvider {
//    val Factory = viewModelFactory {
//        initializer {
//            HomeViewModel(
////                repository = productApplication().container.productsDaoRepository,
////                repo = AppRepository(
////                    context = productApplication().applicationContext,
////                    productsApiService =
////                )
//            )
//        }
//        initializer {
//            ProductDetailsViewModel(
//                this.createSavedStateHandle(),
//                productApplication().container.productsDaoRepository
//            )
//        }
//        initializer {
//            ProductEntryViewModel(
//                context = productApplication().applicationContext
//            )
//        }
//        initializer {
//            ProductEditViewModel(
//                this.createSavedStateHandle(),
//                context = productApplication().applicationContext,
//                productApplication().container.productsDaoRepository
//            )
//        }
//        initializer {
//            LoginViewModel(
//                context = productApplication().applicationContext
////                preferenceDataStore = PreferenceDataStore(productApplication().applicationContext)
//            )
//        }
//        initializer {
//            RegisterViewModel(
//                context = productApplication().applicationContext
//            )
//        }
//    }
//}

///**
// * Extension function to queries for [Application] object and returns an instance of
// * [MyApp].
// */
//fun CreationExtras.productApplication(): MyApp =
//    (this[AndroidViewModelFactory.APPLICATION_KEY] as MyApp)
