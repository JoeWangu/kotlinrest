package com.saddict.djrest.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.saddict.djrest.R
import com.saddict.djrest.ui.navigation.RestNavHost

@Composable
fun RestApp() {
    RestNavHost()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {},
    //    currentScreen: RestAppScreen,
) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_size))
                        .padding(dimensionResource(id = R.dimen.padding_small)),
                    painter = painterResource(R.drawable.profile),
                    contentDescription = null
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.displayLarge,
                )
            }
        },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun RestApp(
//    modifier: Modifier = Modifier,
//    navController: NavHostController = rememberNavController()
//) {
//    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
//    val activity = LocalContext.current as? Activity
//    val productsViewModel: ProductsViewModel = viewModel(factory = ProductsViewModel.Factory)
//    val context = LocalContext.current
//    val backStackEntry by navController.currentBackStackEntryAsState()
//    val currentScreen = RestAppScreen.valueOf(
//        backStackEntry?.destination?.route ?: RestAppScreen.Login.name
//    )
//            val homeUiState by productsViewModel.homeUiState.collectAsState()
//    Scaffold(
//        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
//        topBar = {
//            TopBar(
//                currentScreen = currentScreen,
//                canNavigateBack = navController.previousBackStackEntry != null,
//                navigateUp = { navController.navigateUp() },
//                scrollBehavior = scrollBehavior
//            )
//        },
//    ) { innerPadding ->
//        NavHost(
//            navController = navController,
//            startDestination = RestAppScreen.Login.name,
//            modifier = Modifier.padding(innerPadding)
//        ) {
//            composable(route = RestAppScreen.Login.name) {
//                LoginScreen(onLoginBtnClicked = { navController.navigate(RestAppScreen.Home.name) })
//            }
//            composable(route = RestAppScreen.Home.name) {
//                HomeScreen(
////                    productsUiState = productsViewModel.productsUiState,
////                    retryAction = productsViewModel::getProducts,
////                    refreshAction = productsViewModel::refreshDb,
////                    onSendClick = { summary: String ->
////                        productsViewModel.shareProduct(context = context, summary = summary)
////                    },
////                    onLogOutClick = { activity?.finish() }
//                )
//                //            HomeScreen(productsUiState = homeUiState)
//            }
//        }
//    }
//}