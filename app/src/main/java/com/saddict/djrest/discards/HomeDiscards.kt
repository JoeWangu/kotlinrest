package com.saddict.djrest.discards

/*//@Composable
//fun HomeItems() {
//    val viewModel = hiltViewModel<HomeViewModel>()
//    val products = viewModel.productFlow().collectAsLazyPagingItems()
//    HomeBody(products)
//    if (product != null) {
//        HomeItems(product = product)
//    }
//    HomeBody(uiState = viewModel.uiState)
//}

//@Composable
//fun HomeBody(uiState: ProductsUiState){
//    when(uiState){
//        ProductsUiState.Loading -> LoadingScreen()
//        is ProductsUiState.Success -> HomeItems(product = uiState.products.results)
//        ProductsUiState.Error -> ErrorScreen(retryAction = { TODO })
//    }
//}*/

/*//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen(
//    navigateToLogin: () -> Unit,
//    navigateToItemEntry: () -> Unit,
//    navigateToItemDetails: (Int) -> Unit,
//    modifier: Modifier = Modifier,
//    productsViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
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
//            productsUiState = productsViewModel.productsUiState.collectAsState().value,
////            retryAction = productsViewModel::getProducts,
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
////            onSendClick = { summary: String ->
////                productsViewModel.shareProduct(
////                    context = context,
////                    summary = summary
////                )
////            },
//            onProductClick = navigateToItemDetails,
//            addData = productsViewModel::getMoreData,
//            modifier = Modifier
//                .padding(innerPadding)
//                .fillMaxSize()
//        )
//    }
//}*/

/*//@Composable
//fun HomeBody(
//    addData: () -> Unit,
//    productsUiState: ProductsUiState,
////    retryAction: () -> Unit,
////    refreshAction: () -> Unit,
//    onLogOutClick: () -> Unit,
////    onSendClick: (String) -> Unit,
//    onProductClick: (Int) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    when (productsUiState) {
//        ProductsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
//        is ProductsUiState.Success -> ProductsScreen(
//            products = productsUiState.products,
//            onProductClick = { onProductClick(it.id) },
////            refreshAction = refreshAction,
//            onLogOutClick = onLogOutClick,
////            onSendClick = onSendClick,
//            addData = addData,
//            modifier = modifier.fillMaxSize()
//        )
//
////        is ProductsUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
//    }
//}*/

/*//@Composable
//fun ProductsScreen(
//    addData: () -> Unit,
//    products: List<ProductEntity?>,
////    refreshAction: () -> Unit,
//    onLogOutClick: () -> Unit,
////    onSendClick: (String) -> Unit,
//    onProductClick: (ProductEntity) -> Unit,
//    modifier: Modifier = Modifier
//) {
////    val prodList = listOf(products[0]?.productName, products[0]?.price)
////    val summary = "${stringResource(R.string.app_name)},${prodList}"
//    val scrollState = rememberLazyListState()
//    Column(modifier = modifier) {
//        LazyColumn(
//            state = scrollState,
//            modifier = Modifier
//        ) {
//            items(items = products, key = { it?.id!! }) { product ->
//                if (product != null) {
//                    ProductCard(
//                        product = product,
//                        modifier = Modifier
//                            .padding(dimensionResource(id = R.dimen.padding_small))
//                            .clickable { onProductClick(product) }
//                    )
//                } else {
//                    Text(
//                        text = "There are no results",
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                    )
//                }
//            }
//        }
//        LaunchedEffect(key1 = scrollState) {
//            snapshotFlow { scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
//                .collect { lastIndex ->
//                    if (lastIndex == products.size - 1
////                    / your items size - 1 /
//                    ) {
//                        addData()
//                    }
//                }
//        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(dimensionResource(id = R.dimen.padding_small)),
//            horizontalArrangement = Arrangement.End,
//        ) {
////            Button(onClick = refreshAction) {
////                Text(text = stringResource(id = R.string.refreshDb))
////            }
////            Button(onClick = { onSendClick(summary) }) {
////                Text(text = stringResource(id = R.string.share))
////            }
//            Button(onClick = onLogOutClick) {
//                Text(text = stringResource(id = R.string.logout))
//            }
//        }
////        Row {
////            Button(onClick = { onSendClick(summary) }) {
////                Text(text = stringResource(id = R.string.share))
////            }
////        }
//    }
//}*/

/*//@Composable
//fun ProductCard(product: ProductEntity, modifier: Modifier = Modifier) {
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
//                        .data(product.imageDetail)
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
//                Column(modifier = Modifier
//                    .padding(start = dimensionResource(id = R.dimen.padding_small), bottom = dimensionResource(id = R.dimen.padding_small)))
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
//}*/

/*//@Composable
//fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
//    Column(
//        modifier = modifier,
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
//        )
//        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
//        Button(onClick = retryAction) {
//            Text(stringResource(R.string.retry))
//        }
//    }
//}

//@Composable
//fun LoadingScreen(modifier: Modifier = Modifier) {
//    val infiniteTransition = rememberInfiniteTransition(label = "loader transition")
//    val angle by infiniteTransition.animateFloat(
//        initialValue = 0f,
//        targetValue = 360f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(durationMillis = 2000),
//            repeatMode = RepeatMode.Restart
//        ), label = "loader"
//    )
//    Column(
//        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Image(
//            modifier = Modifier
//                .size(200.dp)
//                .rotate(angle),
//            painter = painterResource(R.drawable.loading_img),
//            contentDescription = stringResource(R.string.loading)
//        )
//    }
//}*/

/*//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun LoadingPreview() {
//    DjRestTheme(dynamicColor = false) {
//        LoadingScreen()
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ErrorPreview() {
//    DjRestTheme(dynamicColor = false) {
//        ErrorScreen({})
//    }
//}*/

//LazyColumn(
//modifier = Modifier
//.fillMaxSize()
//.padding(horizontal = 24.dp),
//verticalArrangement = Arrangement.spacedBy(16.dp),
//horizontalAlignment = Alignment.CenterHorizontally,
//state = scrollState,
//){
//    items(
//        count = products.itemCount,
//        key = products.itemKey{ product -> product.id },
////                    contentType = products.itemContentType()
//    ){ index ->
//        val product = products[index]
//        if (product != null){
//            ProductCard(
//                product = product,
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
//    }
//    item {
//        if (products.loadState.append is LoadState.Loading){
//            CircularProgressIndicator(
////                            modifier = Modifier.align(Alignment.Center)
//            )
//        }
//    }
//}