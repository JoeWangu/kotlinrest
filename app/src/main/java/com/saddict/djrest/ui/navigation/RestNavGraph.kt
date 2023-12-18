package com.saddict.djrest.ui.navigation

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.saddict.djrest.data.manager.PreferenceDataStore
import com.saddict.djrest.utils.utilscreens.LoadingDestination
import com.saddict.djrest.utils.utilscreens.LoadingScreen
import com.saddict.djrest.ui.screens.home.HomeDestination
import com.saddict.djrest.ui.screens.home.HomeScreen
import com.saddict.djrest.ui.screens.product.ProductDetailsDestination
import com.saddict.djrest.ui.screens.product.ProductDetailsScreen
import com.saddict.djrest.ui.screens.product.ProductEditDestination
import com.saddict.djrest.ui.screens.product.ProductEditScreen
import com.saddict.djrest.ui.screens.product.ProductEntryDestination
import com.saddict.djrest.ui.screens.product.ProductEntryScreen
import com.saddict.djrest.ui.screens.registration.LoginDestination
import com.saddict.djrest.ui.screens.registration.LoginScreen
import com.saddict.djrest.ui.screens.registration.RegisterDestination
import com.saddict.djrest.ui.screens.registration.RegisterScreen
import com.saddict.djrest.utils.toastUtil
import kotlinx.coroutines.flow.first

@SuppressLint("CoroutineCreationDuringComposition", "FlowOperatorInvokedInComposition")
@Composable
fun RestNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var pressedTime: Long = 0
    val ctx = LocalContext.current
    val preference = PreferenceDataStore(ctx)
    val activity = LocalContext.current as? Activity
//    val token by preference.preferenceFlow.collectAsState(initial = "")
    LaunchedEffect(key1 = Unit) {
        val token = preference.preferenceFlow.first()
        if (token.isNotBlank()) {
            navController.navigate(HomeDestination.route) {
                popUpTo(LoadingDestination.route) { inclusive = true }
            }
        } else {
            navController.navigate(LoginDestination.route) {
                popUpTo(LoadingDestination.route) { inclusive = true }
            }
        }
    }
    val tokenLot = preference.getToken()
    NavHost(
        navController = navController,
//        startDestination = LoginDestination.route,
        startDestination = if (tokenLot == "") LoginDestination.route else HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = LoadingDestination.route) {
            LoadingScreen()
        }
        composable(route = LoginDestination.route) {
            LoginScreen(
                navigateToHome = { navController.navigate(HomeDestination.route) },
                navigateToRegister = { navController.navigate(RegisterDestination.route) }
            )
        }
        composable(route = HomeDestination.route) {
//            BackHandler(enabled = true) {
//                activity?.finish()
////                Log.i("LOG_TAG", "Clicked back")
//            }
            BackHandler {
                // on below line we are checking if the press time is greater than 2 sec
                if (pressedTime + 2000 > System.currentTimeMillis()) {
                    // if time is greater than 2 sec we are closing the application.
//            super.onBackPressed()
                    activity?.finish()
                } else {
                    // in else condition displaying a toast message.
                    ctx.toastUtil("Press back again to exit")
                }
                // on below line initializing our press time variable
                pressedTime = System.currentTimeMillis()
            }
            HomeScreen(
                navigateToItemDetails = { navController.navigate("${ProductDetailsDestination.route}/${it}") },
                navigateToItemEntry = { navController.navigate(ProductEntryDestination.route) },
//                navigateToLogin = { navController.navigate(LoginDestination.route) }
            )
        }
        composable(
            route = ProductDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ProductDetailsDestination.productIdArg) {
                type = NavType.IntType
            })
        ) {
            ProductDetailsScreen(
                navigateBack = { navController.popBackStack() },
                navigateToEditProduct = { navController.navigate("${ProductEditDestination.route}/${it}") }
            )
        }
        composable(route = ProductEntryDestination.route) {
            ProductEntryScreen(
                navigateBack = { navController.popBackStack() },
            )
        }
        composable(
            route = ProductEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ProductEditDestination.productIdArg) {
                type = NavType.IntType
            })
        ) {
            ProductEditScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(route = RegisterDestination.route) {
            RegisterScreen(
                navigateToHome = { navController.navigate(HomeDestination.route) },
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}
