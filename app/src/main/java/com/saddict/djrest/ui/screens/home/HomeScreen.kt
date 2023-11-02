package com.saddict.djrest.ui.screens.home

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.saddict.djrest.R
import com.saddict.djrest.data.manager.PreferenceDataStore
import com.saddict.djrest.model.local.ProductEntity
import com.saddict.djrest.model.remote.ProductsResult
import com.saddict.djrest.ui.TopBar
import com.saddict.djrest.ui.navigation.NavigationDestination
import com.saddict.djrest.ui.theme.DjRestTheme
import com.saddict.djrest.utils.ExpandItemsButton
import com.saddict.djrest.utils.toastUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

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
*/

@Composable
fun HomeScreen(
    navigateToLogin: () -> Unit,
    navigateToItemEntry: () -> Unit,
    navigateToItemDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    products: LazyPagingItems<ProductsResult> = viewModel.productPagingFlow.collectAsLazyPagingItems(),
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = products.loadState) {
        if (products.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (products.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(modifier = modifier.fillMaxSize()) {
        if (products.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            HomeBase(
                navigateToLogin = navigateToLogin,
                navigateToItemEntry = navigateToItemEntry,
                navigateToItemDetails = navigateToItemDetails,
                refreshAction = {}/*viewModel::deleteAll*/,
                products = products
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBase(
    navigateToLogin: () -> Unit,
    navigateToItemEntry: () -> Unit,
    navigateToItemDetails: (Int) -> Unit,
    products: LazyPagingItems<ProductsResult>,
    refreshAction: () -> Unit,
    modifier: Modifier = Modifier,
//    productsViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
//    val activity = LocalContext.current as? Activity
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val preferenceDataStore = PreferenceDataStore(context)
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
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
    ) { innerPadding ->
        HomeBody(
            products = products,
//            refreshAction = productsViewModel::refreshDb,
            onLogOutClick = {
                coroutineScope.launch {
                    preferenceDataStore.setToken("")
                    context.toastUtil("You have been logged out")
                    delay(2_000L)
//                    activity?.finish()
                    navigateToLogin()
                }
            },
            onProductClick = navigateToItemDetails,
            refreshAction = refreshAction,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun HomeBody(
    products: LazyPagingItems<ProductsResult>,
    onLogOutClick: () -> Unit,
    onProductClick: (Int) -> Unit,
    refreshAction: () -> Unit,
    modifier: Modifier = Modifier
){
    ProductsScreen(
        products = products,
        refreshAction = refreshAction,
        onLogOutClick = onLogOutClick,
        onProductClick = { onProductClick(it.id) },
        modifier = modifier
    )
}

@Composable
fun ProductsScreen(
    products: LazyPagingItems<ProductsResult>,
    refreshAction: () -> Unit,
    onLogOutClick: () -> Unit,
    onProductClick: (ProductsResult) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
//                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = scrollState
        ) {
            items(products.itemCount) { index ->
                val product = products[index]
                product.let {
                    if (it != null) {
                        ProductCard(
                            product = it,
                            modifier = Modifier
                                .padding()
                                .clickable { onProductClick(product!!) }
                        )
                    }
                }
            }
//            item {
//                if (products.loadState.append is LoadState.Loading) {
//                    CircularProgressIndicator(
////                            modifier = Modifier.align(Alignment.Center)
//                    )
//                }
//            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_small)),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(onClick = refreshAction) {
                Text(text = stringResource(id = R.string.refreshDb))
            }
            Button(onClick = onLogOutClick) {
                Text(text = stringResource(id = R.string.logout))
            }
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
                        .data(product.imageDetail.image)
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
                Column(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.padding_small),
                            bottom = dimensionResource(id = R.dimen.padding_small)
                        )
                )
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

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun AppPreview() {
//    DjRestTheme(dynamicColor = false) {
////        val mockData = mutableListOf<ProductsResult>().apply {
////            repeat(5){
////                add(
////                    ProductEntity(
////                        id = 10,
////                        productName = "Product10",
////                        modelNumber = "Model10",
////                        specifications = "Some specifications",
////                        price = 10000.00,
////                        image = 10,
////                        imageDetail = "www.imageUrl.com",
////                        category = 10,
////                        supplier = 10
////                    ).mapToResults()
////                )
////            }
////        }
//
//        ProductsScreen(products = mockData, onProductClick = {}, onLogOutClick = {},
//            addData = {}, refreshAction = {})
//    }
//}
