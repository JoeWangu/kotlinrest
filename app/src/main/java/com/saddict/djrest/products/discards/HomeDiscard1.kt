package com.saddict.djrest.products.discards
//
//object HomeDestination : NavigationDestination {
//    override val route = "home"
//    override val titleRes = R.string.app_name
//}
//
///*//@Composable
////fun HomeItems() {
////    val viewModel = hiltViewModel<HomeViewModel>()
////    val products = viewModel.productFlow().collectAsLazyPagingItems()
////    HomeBody(products)
////    if (product != null) {
////        HomeItems(product = product)
////    }
////    HomeBody(uiState = viewModel.uiState)
////}
//*/
//
//@Composable
//fun HomeScreen(
//    navigateToLogin: () -> Unit,
//    navigateToItemEntry: () -> Unit,
//    navigateToItemDetails: (Int) -> Unit,
//    modifier: Modifier = Modifier,
//    viewModel: HomeViewModel = hiltViewModel(),
//    products: LazyPagingItems<ProductsResult> = viewModel.productPagingFlow.collectAsLazyPagingItems(),
//) {
//    val context = LocalContext.current
//    LaunchedEffect(key1 = products.loadState) {
//        if (products.loadState.refresh is LoadState.Error) {
//            Toast.makeText(
//                context,
//                "Error: " + (products.loadState.refresh as LoadState.Error).error.message,
//                Toast.LENGTH_LONG
//            ).show()
//        }
//    }
//    Box(modifier = modifier.fillMaxSize()) {
//        if (products.loadState.refresh is LoadState.Loading) {
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.Center)
//            )
//        } else {
//            HomeBase(
//                navigateToLogin = navigateToLogin,
//                navigateToItemEntry = navigateToItemEntry,
//                navigateToItemDetails = navigateToItemDetails,
//                refreshAction = {}/*viewModel::deleteAll*/,
//                products = products
//            )
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeBase(
//    navigateToLogin: () -> Unit,
//    navigateToItemEntry: () -> Unit,
//    navigateToItemDetails: (Int) -> Unit,
//    products: LazyPagingItems<ProductsResult>,
//    refreshAction: () -> Unit,
//    modifier: Modifier = Modifier,
////    productsViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
//) {
//    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
////    val activity = LocalContext.current as? Activity
//    val context = LocalContext.current
//    val coroutineScope = rememberCoroutineScope()
//    val preferenceDataStore = PreferenceDataStore(context)
//    Scaffold(
//        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
//        topBar = {
//            TopBar(
//                title = stringResource(HomeDestination.titleRes),
//                canNavigateBack = false,
//                scrollBehavior = scrollBehavior
//            )
//        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = navigateToItemEntry,
//                shape = MaterialTheme.shapes.extraSmall,
//                modifier = Modifier
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = stringResource(id = R.string.product_entry_title)
//                )
//            }
//        },
//    ) { innerPadding ->
//        HomeBody(
//            products = products,
////            refreshAction = productsViewModel::refreshDb,
//            onLogOutClick = {
//                coroutineScope.launch {
//                    preferenceDataStore.setToken("")
//                    context.toastUtil("You have been logged out")
//                    delay(2_000L)
////                    activity?.finish()
//                    navigateToLogin()
//                }
//            },
//            onProductClick = navigateToItemDetails,
//            refreshAction = refreshAction,
//            modifier = Modifier
//                .padding(innerPadding)
//                .fillMaxSize()
//        )
//    }
//}
//
////@Composable
////fun HomePage(){}
//
//@Composable
//fun HomeBody(
//    products: LazyPagingItems<ProductsResult>,
//    onLogOutClick: () -> Unit,
//    onProductClick: (Int) -> Unit,
//    refreshAction: () -> Unit,
//    modifier: Modifier = Modifier
//){
//    ProductsScreen(
//        products = products,
//        refreshAction = refreshAction,
//        onLogOutClick = onLogOutClick,
//        onProductClick = { onProductClick(it.id) },
//        modifier = modifier
//    )
//}
//
//@Composable
//fun ProductsScreen(
//    products: LazyPagingItems<ProductsResult>,
//    refreshAction: () -> Unit,
//    onLogOutClick: () -> Unit,
//    onProductClick: (ProductsResult) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    val scrollState = rememberLazyListState()
//    Box(modifier = modifier.fillMaxSize()) {
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize(),
////                .padding(horizontal = 24.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            state = scrollState
//        ) {
//            items(products.itemCount) { index ->
//                val product = products[index]
//                product.let {
//                    if (it != null) {
//                        ProductCard(
//                            product = it,
//                            modifier = Modifier
//                                .padding()
//                                .clickable { onProductClick(product!!) }
//                        )
//                    }
//                }
//            }
////            item {
////                if (products.loadState.append is LoadState.Loading) {
////                    CircularProgressIndicator(
//////                            modifier = Modifier.align(Alignment.Center)
////                    )
////                }
////            }
//        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(dimensionResource(id = R.dimen.padding_small)),
//            horizontalArrangement = Arrangement.End,
//            verticalAlignment = Alignment.Bottom
//        ) {
//            Button(onClick = refreshAction) {
//                Text(text = stringResource(id = R.string.refreshDb))
//            }
//            Button(onClick = onLogOutClick) {
//                Text(text = stringResource(id = R.string.logout))
//            }
//        }
//    }
//}
//
//@Composable
//fun ProductCard(product: ProductsResult, modifier: Modifier = Modifier) {
//    Card(
//        modifier = modifier,
//        shape = MaterialTheme.shapes.small,
//        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.padding_small))
//    ) {
//        var expanded by remember { mutableStateOf(false) }
//        val color by animateColorAsState(
//            targetValue = if (expanded) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onPrimary,
//            label = "expandColor",
//        )
//        Column(
//            modifier = Modifier
//                .animateContentSize(
//                    animationSpec = spring(
//                        dampingRatio = Spring.DampingRatioLowBouncy,
//                        stiffness = Spring.StiffnessMediumLow
//                    )
//                )
//                .background(color = color)
//        )
//        {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(dimensionResource(id = R.dimen.padding_small)),
//            ) {
//                AsyncImage(
//                    model = ImageRequest.Builder(context = LocalContext.current)
//                        .data(product.imageDetail.image)
//                        .crossfade(true).build(),
//                    error = painterResource(R.drawable.ic_broken_image),
//                    placeholder = painterResource(R.drawable.loading_img),
//                    contentDescription = stringResource(R.string.p_image),
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .size(dimensionResource(id = R.dimen.image_size))
//                        .padding(dimensionResource(id = R.dimen.padding_small))
//                        .clip(MaterialTheme.shapes.medium)
//                )
//                Column(modifier = Modifier) {
//                    Text(
//                        text = "${product.productName}",
//                        style = MaterialTheme.typography.displayMedium,
//                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small))
//                    )
//                    Text(
//                        text = "${product.price}",
//                        style = MaterialTheme.typography.bodyLarge
//                    )
//                }
//                Spacer(modifier = Modifier.weight(1f))
//                ExpandItemsButton(expanded = expanded, onClick = { expanded = !expanded })
//            }
//            if (expanded) {
//                Column(
//                    modifier = Modifier
//                        .padding(
//                            start = dimensionResource(id = R.dimen.padding_small),
//                            bottom = dimensionResource(id = R.dimen.padding_small)
//                        )
//                )
//                {
//                    Text(
//                        text = "${product.specifications}",
//                        style = MaterialTheme.typography.bodyLarge
//                    )
//                    Text(
//                        text = "${product.modelNumber}",
//                        style = MaterialTheme.typography.bodyLarge
//                    )
//                }
//            }
//        }
//    }
//}
//
////@Preview(showBackground = true, showSystemUi = true)
////@Composable
////fun AppPreview() {
////    DjRestTheme(dynamicColor = false) {
//////        val mockData = mutableListOf<ProductsResult>().apply {
//////            repeat(5){
//////                add(
//////                    ProductEntity(
//////                        id = 10,
//////                        productName = "Product10",
//////                        modelNumber = "Model10",
//////                        specifications = "Some specifications",
//////                        price = 10000.00,
//////                        image = 10,
//////                        imageDetail = "www.imageUrl.com",
//////                        category = 10,
//////                        supplier = 10
//////                    ).mapToResults()
//////                )
//////            }
//////        }
////
////        ProductsScreen(products = mockData, onProductClick = {}, onLogOutClick = {},
////            addData = {}, refreshAction = {})
////    }
////}
