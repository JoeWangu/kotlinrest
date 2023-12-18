package com.saddict.djrest.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.saddict.djrest.R
import com.saddict.djrest.model.remote.ImageArrayResults
import com.saddict.djrest.model.remote.ProductsResult
import com.saddict.djrest.ui.navigation.NavigationDestination
import com.saddict.djrest.ui.theme.DjRestTheme
import com.saddict.djrest.utils.toastUtilLong
import com.saddict.djrest.utils.utilscreens.RestBottomNavigationBar
import com.saddict.djrest.utils.utilscreens.RestTopAppBar
import kotlinx.coroutines.flow.flowOf

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

/*
//@Composable
//fun HomeItems() {
//    val viewModel = hiltViewModel<HomeViewModel>()
//    val products = viewModel.productFlow().collectAsLazyPagingItems()
//    HomeBody(products)
//    if (product != null) {
//        HomeItems(product = product)
//    }
//    HomeBody(uiState = viewModel.uiState)
//}
*/

@Composable
fun HomeScreen(
//    navigateToLogin: () -> Unit,
    navigateToItemEntry: () -> Unit,
    navigateToItemDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    products: LazyPagingItems<ProductsResult> = viewModel.productPagingFlow.collectAsLazyPagingItems(),
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = products.loadState) {
        if (products.loadState.refresh is LoadState.Error) {
            context.toastUtilLong("Error: " + (products.loadState.refresh as LoadState.Error).error.message)
        }
    }
    Box(modifier = modifier) {
        if (products.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            HomeBase(
//                navigateToLogin = navigateToLogin,
                navigateToItemEntry = navigateToItemEntry,
                navigateToItemDetails = navigateToItemDetails,
//                refreshAction = viewModel::deleteAll,
                products = products
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBase(
//    navigateToLogin: () -> Unit,
    navigateToItemEntry: () -> Unit,
    navigateToItemDetails: (Int) -> Unit,
    products: LazyPagingItems<ProductsResult>,
//    refreshAction: () -> Unit,
    modifier: Modifier = Modifier,
//    productsViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
//    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
//    val activity = LocalContext.current as? Activity
//    val context = LocalContext.current
//    val coroutineScope = rememberCoroutineScope()
//    val preferenceDataStore = PreferenceDataStore(context)
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            RestTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.extraSmall,
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.product_entry_title)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            RestBottomNavigationBar(
                navigateToHome = { /*TODO*/ },
                navigateToFavourites = { /*TODO*/ },
                navigateToTrending = { /*TODO*/ },
                navigateToAccount = { /*TODO*/ })
        }
    ) { innerPadding ->
        HomeBody(
            products = products,
//            refreshAction = productsViewModel::refreshDb,
//            onLogOutClick = {
//                coroutineScope.launch {
//                    preferenceDataStore.setToken("")
//                    context.toastUtil("You have been logged out")
//                    delay(2_000L)
////                    activity?.finish()
//                    navigateToLogin()
//                }
//            },
            onProductClick = navigateToItemDetails,
//            refreshAction = refreshAction,
            modifier = Modifier
                .padding(innerPadding)
//                .fillMaxSize()
        )
    }
}

//@Composable
//fun HomePage(){}

@Composable
fun HomeBody(
    products: LazyPagingItems<ProductsResult>,
//    onLogOutClick: () -> Unit,
    onProductClick: (Int) -> Unit,
//    refreshAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    ProductScreen(
        products = products,
//        refreshAction = refreshAction,
//        onLogOutClick = onLogOutClick,
        onProductClick = { onProductClick(it.id) },
        modifier = modifier
    )
}

@Composable
fun ProductScreen(
    products: LazyPagingItems<ProductsResult>,
//    refreshAction: () -> Unit,
//    onLogOutClick: () -> Unit,
    onProductClick: (ProductsResult) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()
//    Box(modifier = modifier.fillMaxSize()) {
    LazyColumn(
        modifier = modifier,
//                .fillMaxSize(),
//                .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = scrollState
    ) {
        items(products.itemCount, key = products.itemKey { it.id }) { index ->
            val product = products[index]
            product.let {
                if (it != null) {
                    ProductCard(
                        product = it,
                        modifier = Modifier
                            .padding()
                            .clickable { onProductClick(product!!) }
                    )
                } else {
                    Text(text = "Item not found")
                }
            }
        }
        item {
            if (products.loadState.append is LoadState.Loading) {
                CircularProgressIndicator(
//                            modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
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
}

@Composable
fun ProductCard(
    product: ProductsResult,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
//                .background(color = MaterialTheme.colorScheme.primaryContainer)
        )
        {
            Card(
                modifier = Modifier,
                shape = MaterialTheme.shapes.small,
//                elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.padding_small))
            ) {
                Box(modifier = Modifier) {
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(product.imageDetail.image)
                            .crossfade(true).build(),
                        error = painterResource(R.drawable.ic_broken_image),
                        placeholder = painterResource(R.drawable.loading_img),
                        contentDescription = stringResource(R.string.p_image),
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(width = 0.dp, height = 250.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = Color.Blue,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(30.dp)
                            .align(Alignment.TopEnd)
                            .clickable {}
                    )
                }
//                Spacer(modifier = Modifier.weight(1f))
            }
            Row(
                modifier = Modifier
            ) {
                Text(
                    text = "${product.productName}",
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.padding_small),
                            start = dimensionResource(id = R.dimen.padding_medium),
                        )
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${product.price}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.padding_small),
                            end = dimensionResource(id = R.dimen.padding_medium),
                        )
                )
            }
            Row(modifier = Modifier) {
                Text(
                    text = "${product.specifications}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.padding_small),
                            start = dimensionResource(id = R.dimen.padding_medium),
                        )
                )
            }
        }
    }
}

/*@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    DjRestTheme(dynamicColor = false) {
//        val mockData = mutableListOf<ProductsResult>().apply {
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
//                    ).mapToResults()
//                )
//            }
//        }
        val mockData = mutableListOf<ProductsResult>().apply {
            add(
                ProductsResult(
                    id = 1,
                    productName = "Product1",
                    modelNumber = "Model1",
                    specifications = "Some specifications",
                    price = 10000.00,
                    image = 1,
                    imageDetail = ImageArrayResults(
                        1,
                        "https://www.startpage.com/av/proxy-image?piurl=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.4siKIW3oZ4kEo0vkEVQ5hgHaLH%26pid%3DApi&sp=1699779802T5887f2d8e0253540279d57cb41e3dca7d7105557a9ef7ff534d84484794e7a83",
                    ),
                    category = 1,
                    supplier = 1
                )
            )
            add(
                ProductsResult(
                    id = 2,
                    productName = "Product2",
                    modelNumber = "Model2",
                    specifications = "Some specifications",
                    price = 10000.00,
                    image = 2,
                    imageDetail = ImageArrayResults(
                        1,
                        "https://images.pexels.com/photos/18512532/pexels-photo-18512532/free-photo-of-a-mountain-range-with-a-small-pond-in-the-foreground.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load",
                    ),
                    category = 1,
                    supplier = 1
                )
            )
            add(
                ProductsResult(
                    id = 3,
                    productName = "Product3",
                    modelNumber = "Model3",
                    specifications = "Some specifications",
                    price = 10000.00,
                    image = 3,
                    imageDetail = ImageArrayResults(
                        1,
                        "https://images.pexels.com/photos/8486108/pexels-photo-8486108.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load",
                    ),
                    category = 1,
                    supplier = 1
                )
            )
            add(
                ProductsResult(
                    id = 4,
                    productName = "Product4",
                    modelNumber = "Model4",
                    specifications = "Some specifications",
                    price = 10000.00,
                    image = 4,
                    imageDetail = ImageArrayResults(
                        1,
                        "https://images.pexels.com/photos/18311326/pexels-photo-18311326/free-photo-of-a-woman-with-red-hair-and-green-jacket.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load",
                    ),
                    category = 1,
                    supplier = 1
                )
            )
            add(
                ProductsResult(
                    id = 5,
                    productName = "Product5",
                    modelNumber = "Model5",
                    specifications = "Some specifications",
                    price = 10000.00,
                    image = 5,
                    imageDetail = ImageArrayResults(
                        1,
                        "https://images.pexels.com/photos/11566663/pexels-photo-11566663.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load",
                    ),
                    category = 1,
                    supplier = 1
                )
            )
            add(
                ProductsResult(
                    id = 6,
                    productName = "Product6",
                    modelNumber = "Model6",
                    specifications = "Some specifications",
                    price = 10000.00,
                    image = 6,
                    imageDetail = ImageArrayResults(
                        1,
                        "https://images.pexels.com/photos/19001116/pexels-photo-19001116/free-photo-of-metropolitana.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load",
                    ),
                    category = 1,
                    supplier = 1
                )
            )
        }
        val pagingData = PagingData.from(mockData)
        val lazyPagingItems = flowOf(pagingData).collectAsLazyPagingItems()

//        ProductsScreen(products = lazyPagingItems, onProductClick = {}, onLogOutClick = {},
//            refreshAction = {})
        HomeBase(
//            navigateToLogin = {},
            navigateToItemEntry = {},
            navigateToItemDetails = {},
            products = lazyPagingItems,
//            refreshAction = {}
        )
    }
}*/
