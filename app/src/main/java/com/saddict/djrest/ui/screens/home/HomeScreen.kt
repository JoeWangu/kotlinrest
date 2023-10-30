package com.saddict.djrest.ui.screens.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.saddict.djrest.R
import com.saddict.djrest.model.local.ProductEntity
import com.saddict.djrest.model.remote.ProductsResult
import com.saddict.djrest.ui.navigation.NavigationDestination
import com.saddict.djrest.ui.screens.extra.LoadingScreen
import com.saddict.djrest.utils.ExpandItemsButton

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
//    val products = viewModel.productFlow().collectAsLazyPagingItems()
//    HomeBody(products)
//    if (product != null) {
//        HomeItems(product = product)
//    }
    HomeBody(uiState = viewModel.uiState)
}

@Composable
fun HomeBody(uiState: ProductsUiState){
    when(uiState){
        ProductsUiState.Loading -> LoadingScreen()
        is ProductsUiState.Success -> HomeItems(product = uiState.products.results)
        ProductsUiState.Error -> ErrorScreen(retryAction = { /*TODO*/ })
    }
}

@Composable
fun HomeItems(product: List<ProductsResult>, modifier: Modifier = Modifier){
    LazyColumn(){
        items(product){
            ProductCard(product = it)
        }
    }
}

@Composable
fun ProductCard(product: ProductsResult, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.padding_small))
    ) {
        var expanded by remember { mutableStateOf(false) }
        val color by animateColorAsState(
            targetValue = if (expanded) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onPrimary,
            label = "expandColor",
        )
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessMediumLow
                    )
                )
                .background(color = color)
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_small)),
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(product.imageDetail)
                        .crossfade(true).build(),
                    error = painterResource(R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = stringResource(R.string.p_image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_size))
                        .padding(dimensionResource(id = R.dimen.padding_small))
                        .clip(MaterialTheme.shapes.medium)
                )
                Column(modifier = Modifier) {
                    Text(
                        text = "${product.productName}",
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small))
                    )
                    Text(
                        text = "${product.price}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                ExpandItemsButton(expanded = expanded, onClick = { expanded = !expanded })
            }
            if (expanded) {
                Column(modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.padding_small), bottom = dimensionResource(id = R.dimen.padding_small)))
                {
                    Text(
                        text = "${product.specifications}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "${product.modelNumber}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

//@Composable
//fun HomeBody(
//    products: LazyPagingItems<ProductsResult>,
//    modifier: Modifier = Modifier
//) {
////    LazyColumn(modifier = modifier) {
//    when (products.loadState.refresh) { //FIRST LOAD
//        is LoadState.Error -> {
//            Column(
//                modifier = modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(text = stringResource(R.string.something_went_wrong))
//            }
//        }
//
//        is LoadState.Loading -> { // Loading UI
////                items {
//            Column(
//                modifier = modifier.fillMaxSize(),
////                            .fillParentMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center,
//            ) {
//                Text(
//                    modifier = Modifier
//                        .padding(8.dp),
//                    text = "Refresh Loading"
//                )
//
//                CircularProgressIndicator(color = Color.Black)
//            }
////                }
//        }
//
//        else -> {
//            Text(text = "$products")
////                LazyColumn(modifier = modifier){
////                    itemsIndexed(products){ index, item ->
////                        item?.
////                    }
////                }
////                itemsIndexed(products) { index, product ->
////                    product.
////                    Text(
////                        modifier = Modifier
////                            .height(75.dp),
////                        text = product. ?: "",
////                    )
////                }
//        }
//    }

//        when (products.loadState.append) { // Pagination
//            is LoadState.Error -> {
//                //TODO Pagination Error Item
//                //state.error to get error message
//            }
//            is LoadState.Loading -> { // Pagination Loading UI
//                item {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center,
//                    ) {
//                        Text(text = "Pagination Loading")
//
//                        CircularProgressIndicator(color = Color.Black)
//                    }
//                }
//            }
//            else -> {}
//        }
//    }
//}

//@OptIn(ExperimentalMaterial3Api::class)
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
//}
//
//@Composable
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
//}
//
//@Composable
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
//                    if (lastIndex == products.size - 1/* your items size - 1 */) {
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
//
//}

//@Composable
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
//}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

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
//}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun AppPreview() {
//    DjRestTheme(dynamicColor = false) {
//        val mockData = mutableListOf<ProductEntity>().apply {
//            repeat(5){
//                add(
//                    ProductEntity(
//                        id = 10,
//                        productName = "Product10",
//                        modelNumber = "Model10",
//                        specifications = "Some specifications",
//                        price = 10000.00,
//                        image = 10,
//                        imageDetail = "www.imageUrl.com",
//                        category = 10,
//                        supplier = 10
//                    )
//                )
//            }
//        }
//        ProductsScreen(products = mockData, onProductClick = {}, onSendClick = {}, onLogOutClick = {},
//            addData = {})
//    }
//}

//@Preview(showBackground = true, showSystemUi = true)
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
//}