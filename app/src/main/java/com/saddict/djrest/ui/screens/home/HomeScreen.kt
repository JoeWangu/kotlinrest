package com.saddict.djrest.ui.screens.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.saddict.djrest.R
import com.saddict.djrest.data.manager.PreferenceDataStore
import com.saddict.djrest.model.local.ProductEntity
import com.saddict.djrest.ui.TopBar
import com.saddict.djrest.ui.navigation.NavigationDestination
import com.saddict.djrest.ui.screens.AppViewModelProvider
import com.saddict.djrest.utils.toastUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToLogin: () -> Unit,
    navigateToItemEntry: () -> Unit,
    navigateToItemDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
    productsViewModel: ProductsViewModel = viewModel(factory = AppViewModelProvider.Factory)
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
            productsUiState = productsViewModel.productsUiState.collectAsState().value,
//            retryAction = productsViewModel::getProducts,
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
            onSendClick = { summary: String ->
                productsViewModel.shareProduct(
                    context = context,
                    summary = summary
                )
            },
            onProductClick = navigateToItemDetails,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun HomeBody(
    productsUiState: ProductsUiState,
//    retryAction: () -> Unit,
//    refreshAction: () -> Unit,
    onLogOutClick: () -> Unit,
    onSendClick: (String) -> Unit,
    onProductClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    when (productsUiState) {
        ProductsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is ProductsUiState.Success -> ProductsBody(
            products = productsUiState.products,
            onProductClick = onProductClick,
//            refreshAction = refreshAction,
            onLogOutClick = onLogOutClick,
            onSendClick = onSendClick,
            modifier = modifier.fillMaxSize()
        )

//        is ProductsUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
//    ProductsScreen(products = productsUiState., modifier.fillMaxSize())
}

@Composable
fun ProductsBody(
    products: List<ProductEntity?>,
    onProductClick: (Int) -> Unit,
//    refreshAction: () -> Unit,
    onLogOutClick: () -> Unit,
    onSendClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier
//    ) {
    if (products.isEmpty()) {
        Text(
            text = stringResource(R.string.no_item_description),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
    } else {
        ProductsScreen(
            products = products,
            onProductClick = { onProductClick(it.id) },
//            refreshAction = refreshAction,
            onLogOutClick = onLogOutClick,
            onSendClick = onSendClick,
            modifier = modifier.fillMaxWidth()
        )
    }
//    }
}

@Composable
fun ProductsScreen(
    products: List<ProductEntity?>,
//    refreshAction: () -> Unit,
    onLogOutClick: () -> Unit,
    onSendClick: (String) -> Unit,
    onProductClick: (ProductEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    val prodList = listOf(products[0]?.productName, products[0]?.price)
    val summary = "${stringResource(R.string.app_name)},${prodList}"
    Column(modifier = modifier) {
        LazyColumn(modifier = Modifier) {
            items(items = products, key = { it?.id!! }) { product ->
                if (product != null) {
                    ProductCard(
                        //                product = products.results[it],
                        product = product,
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.padding_small))
                            .clickable { onProductClick(product) }
                    )
                } else {
                    Text(text = "There are no results")
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_small)),
            horizontalArrangement = Arrangement.End,
        ) {
//            Button(onClick = refreshAction) {
//                Text(text = stringResource(id = R.string.refreshDb))
//            }
            Button(onClick = { onSendClick(summary) }) {
                Text(text = stringResource(id = R.string.share))
            }
            Button(onClick = onLogOutClick) {
                Text(text = stringResource(id = R.string.logout))
            }
        }
//        Row {
//            Button(onClick = { onSendClick(summary) }) {
//                Text(text = stringResource(id = R.string.share))
//            }
//        }
    }

}

@Composable
fun ProductCard(product: ProductEntity, modifier: Modifier = Modifier) {
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
                ProductImage(image = product.imageDetail)
                ProductInfo(product = product)
                Spacer(modifier = Modifier.weight(1f))
                ProductItemButton(expanded = expanded, onClick = { expanded = !expanded })
            }
            if (expanded) {
                ExtraProductInfo(
                    product = product,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
                )
            }
        }
    }
}

@Composable
fun ProductImage(image: String?, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(image)
            .crossfade(true).build(),
        error = painterResource(R.drawable.ic_broken_image),
        placeholder = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.p_image),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(dimensionResource(id = R.dimen.image_size))
            .padding(dimensionResource(id = R.dimen.padding_small))
            .clip(MaterialTheme.shapes.medium)
    )
}

@Composable
fun ProductInfo(product: ProductEntity, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        val productList = listOf(product)
        productList.forEach {
            Text(
                text = "${it.productName}",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small))
            )
            Text(
                text = "${it.modelNumber}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun ExtraProductInfo(product: ProductEntity, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        val productList = listOf(product)
        productList.forEach {
            Text(
                text = "${it.specifications}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "${it.price}",
                style = MaterialTheme.typography.bodyLarge
            )
//            Text(
//                text = "${it.image}",
//                style = MaterialTheme.typography.bodyLarge
//            )
//            Text(
//                text = "${it.imageDetail[0].image}",
//                style = MaterialTheme.typography.bodyLarge
//            )
//            Text(
//                text = "${it.category}",
//                style = MaterialTheme.typography.bodyLarge
//            )
//            Text(
//                text = "${it.supplier}",
//                style = MaterialTheme.typography.bodyLarge
//            )
        }
    }
}

@Composable
fun ProductItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

//@Composable
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

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "loader transition")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000),
            repeatMode = RepeatMode.Restart
        ), label = "loader"
    )
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .rotate(angle),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun AppPreview() {
//    DjRestTheme(dynamicColor = false) {
//        val images = ImageArrayResults(1, "image url")
////        val mockData = Products(
////            listOf(
////                Result(
////                    1, "barcode1", "product1", "description1", 134.33, 1, images, 1, 1
////                )
////            ), 1, "1", "1"
////        )
//        val mockData = Products(
//            mutableListOf<ProductsResult>().apply {
//                repeat(5) {
//                    add(
//                        ProductsResult(
//                            1, "barcode1", "product1", "description1", 134.33, 1, images, 1, 1
//                        )
//                    )
//                }
//            }, 1, "1", "1"
//        )
//
//
//        ProductsScreen(products = mockData)
//    }
//}
//
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
