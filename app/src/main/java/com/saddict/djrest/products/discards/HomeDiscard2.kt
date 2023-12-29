package com.saddict.djrest.products.discards

//object HomeDestination : NavigationDestination {
//    override val route = "home"
//    override val titleRes = R.string.app_name
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun HomeScreen(modifier: Modifier = Modifier) {
//    val ctx = LocalContext.current
//    val snackBarHostState = remember {
//        SnackbarHostState()
//    }
//    val scope = rememberCoroutineScope()
//
//    var selectedItem by remember {
//        mutableIntStateOf(0)
//    }
//    val menuTitle = listOf("Home", "Favourite", "Explore", "Profile")
//    val menuIcons = listOf(
//        R.drawable.home_circle,
//        R.drawable.heart,
//        R.drawable.trending_up,
//        R.drawable.account_circle
//    )
//
//    Scaffold(
//        topBar = {
//            TopBar(
//                title = "Top Dev",
//                canNavigateBack = true,
//                scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
//            )
//        },
//        snackbarHost = { SnackbarHost(snackBarHostState) },
//        content = { innerPadding ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(innerPadding),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = stringResource(id = R.string.no_item_description),
//                    fontSize = 16.sp,
//                    modifier = Modifier.padding(20.dp)
//                )
//                Button(
//                    onClick = {
//                        scope.launch {
//                            val result = snackBarHostState.showSnackbar(
//                                message = "Hurry! Our msg ",
//                                actionLabel = "Action",
//                                withDismissAction = true,
//                                duration = SnackbarDuration.Indefinite
//                            )
//                            when (result) {
//                                SnackbarResult.ActionPerformed -> {}
//                                SnackbarResult.Dismissed -> {}
//                            }
//                        }
//                    },
//                    modifier = Modifier.wrapContentSize()
//                ) {
//                    Text(text = "Show SnackBar")
//                }
//            }
//        },
//        floatingActionButton =
//        {
//            FloatingActionButton(onClick = { ctx.toastUtil("Floating btn click") },
//                shape = MaterialTheme.shapes.extraSmall,
//                content = {
//                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
//                })
//        },
//        floatingActionButtonPosition = FabPosition.End,
//        bottomBar = {
//            NavigationBar {
//                menuIcons.forEachIndexed{index, item ->
//                    NavigationBarItem(
//                        selected = selectedItem == index,
//                        onClick = { selectedItem = index },
//                        icon = {
//                            Icon(
//                                imageVector = ImageVector.vectorResource(item),
//                                contentDescription = null
//                            )
//                        },
//                        label = if(selectedItem == index){
//                            { Text(menuTitle[index]) }
//                        }else{
//                            null
//                        }
//                    )
//                }
//            }
////         BottomAppBar(
////             content = {
////                 Row(
////                     modifier = Modifier.fillMaxWidth(1f),
////                     horizontalArrangement = Arrangement.SpaceEvenly,
////                     verticalAlignment = Alignment.CenterVertically
////                 ) {
////                     IconButton(onClick = { /*TODO*/ }) {
////                         Icon(imageVector = Icons.Default.Home, contentDescription = null)
////                     }
////                     IconButton(onClick = { /*TODO*/ }) {
////                         Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
////                     }
////                     IconButton(onClick = { /*TODO*/ }) {
////                         Icon(imageVector = Icons.Default.Star, contentDescription = null)
////                     }
////                     IconButton(onClick = { /*TODO*/ }) {
////                         Icon(imageVector = Icons.Default.Settings, contentDescription = null)
////                     }
////                 }
////             }
////         )
//        },
////        containerColor = Color.Cyan,
////        contentColor = Color.Blue,
////        contentWindowInsets = WindowInsets(0),
//        modifier = modifier
//    )
//}
