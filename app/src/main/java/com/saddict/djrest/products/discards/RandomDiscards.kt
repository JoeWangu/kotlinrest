package com.saddict.djrest.products.discards

/*
// *SCAFFOLD WITH NAVIGATION DIRECTLY INSIDE*
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MyApp(
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
*/

/*
// * DEPRECATED WAY OF PREVENTING IMMEDIATE APP CLOSE *
//    private var pressedTime: Long = 0
// on below line we are calling on back press method.
//    @Deprecated("Deprecated in Java")
//    override fun onBackPressed() {
//        // on below line we are checking if the press time is greater than 2 sec
//        if (pressedTime + 2000 > System.currentTimeMillis()) {
//            // if time is greater than 2 sec we are closing the application.
//            super.onBackPressed()
//            finish()
//        } else {
//            // in else condition displaying a toast message.
//            Toast.makeText(baseContext, "Press back again to exit", Toast.LENGTH_SHORT).show()
//        }
//        // on below line initializing our press time variable
//        pressedTime = System.currentTimeMillis()
//    }
*/
