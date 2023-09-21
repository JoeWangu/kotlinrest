package com.saddict.djrest.ui.screens.login

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.saddict.djrest.R
import com.saddict.djrest.ui.TopBar
import com.saddict.djrest.ui.navigation.NavigationDestination
import com.saddict.djrest.ui.screens.AppViewModelProvider
import com.saddict.djrest.ui.screens.home.HomeDestination
import com.saddict.djrest.utils.toastUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object LoginDestination : NavigationDestination {
    override val route = "login"
    override val titleRes = R.string.login
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
//    onLoginBtnClicked: (username: String, password: String) -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false,
            )
        },
    ) { innerPadding ->
        LoginBody(
//            onLoginBtnClicked = onLoginBtnClicked,
            navigateToHome = navigateToHome,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun LoginBody(
//    onLoginBtnClicked: (username: String, password: String) -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.note_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            LoginInput(
                navigateToHome = navigateToHome
//                onLoginBtnClicked = onLoginBtnClicked
            )
        }
    }
}

@Composable
fun LoginInput(
//    onLoginBtnClicked: (username: String, password: String) -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val ctx = LocalContext.current
//    val preferenceDataStore = PreferenceDataStore(ctx)
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.padding_small)),
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Column(
            modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            FieldTextOutlined(
                value = username,
                onValueChange = { username = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                leadingIcon = Icons.Filled.AccountCircle,
                label = R.string.username
            )
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
            FieldTextOutlined(
                value = password,
                onValueChange = { password = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                leadingIcon = Icons.Filled.Lock,
                label = R.string.password
            )
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
            Button(
                onClick = {
//                    coroutineScope.launch {
//                        viewModel.login(username, password)
////                        navigateToHome()
                    coroutineScope.launch {
                        viewModel.login(username, password)
                        viewModel.uiState.collect { state ->
                            when (state) {
                                LoginUiState.Error -> ctx.toastUtil("Incorrect username or password")
                                LoginUiState.Loading -> ctx.toastUtil("Waiting for response")
                                is LoginUiState.Success -> {
//                                    preferenceDataStore.preferenceFlow.collect{
//                                        ctx.toastUtil(it)
                                    ctx.toastUtil("Login Success")
                                    delay(2_000L)
                                    navigateToHome()
//                                    }
                                }
                            }
                        }
//                        if (viewModel.uiState.collect{ state ->
//                            })
                    }

                },
                modifier = Modifier
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                )
            }
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
            Row {
                Text(
                    text = stringResource(id = R.string.registerTxt),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                )
                Text(
                    text = stringResource(id = R.string.reg_here),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
            }

        }
    }
}

@Composable
fun FieldTextOutlined(
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    leadingIcon: ImageVector,
    @StringRes label: Int,
    value: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(label)) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
            )
        },
        modifier = modifier
    )
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun Screen(){
//    DjRestTheme(dynamicColor = false) {
//        LoginScreen()
//    }
//}