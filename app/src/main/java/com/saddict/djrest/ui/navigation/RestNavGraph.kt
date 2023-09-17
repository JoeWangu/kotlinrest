package com.saddict.djrest.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.saddict.djrest.data.PreferenceDataStore
import com.saddict.djrest.ui.screens.home.HomeDestination
import com.saddict.djrest.ui.screens.home.HomeScreen
import com.saddict.djrest.ui.screens.login.LoginDestination
import com.saddict.djrest.ui.screens.login.LoginScreen
import com.saddict.djrest.ui.screens.product.ProductDetailsDestination
import com.saddict.djrest.ui.screens.product.ProductDetailsScreen
import com.saddict.djrest.ui.screens.product.ProductEditDestination
import com.saddict.djrest.ui.screens.product.ProductEditScreen
import com.saddict.djrest.ui.screens.product.ProductEntryDestination
import com.saddict.djrest.ui.screens.product.ProductEntryScreen

@SuppressLint("CoroutineCreationDuringComposition", "FlowOperatorInvokedInComposition")
@Composable
fun RestNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val ctx = LocalContext.current
    val preference = PreferenceDataStore(ctx)
    val token by preference.preferenceFlow.collectAsState(initial = "")

    NavHost(
        navController = navController,
//        startDestination = LoginDestination.route,
        startDestination = if (token == "") LoginDestination.route else HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = LoginDestination.route) {
            LoginScreen(
                navigateToHome = { navController.navigate(HomeDestination.route) }
//                onLoginBtnClicked = { navController.navigate(HomeDestination.route) }
            )
        }
        composable(route = HomeDestination.route) {
            HomeScreen(
                onNavigateUp = { navController.navigateUp() },
                navigateToItemDetails = { navController.navigate("${ProductDetailsDestination.route}/${it}") },
                navigateToItemEntry = { navController.navigate(ProductEntryDestination.route) }
            )
        }
        composable(
            route = ProductDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ProductDetailsDestination.productIdArg){
                type = NavType.IntType
            })
        ){
            ProductDetailsScreen(
                onNavigateUp = { navController.navigateUp() },
                navigateToEditProduct = { navController.navigate("${ProductEditDestination.route}/${it}") }
            )
        }
        composable(route = ProductEntryDestination.route){
            ProductEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = ProductEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ProductEditDestination.productIdArg){
                type = NavType.IntType
            })
        ){
            ProductEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
