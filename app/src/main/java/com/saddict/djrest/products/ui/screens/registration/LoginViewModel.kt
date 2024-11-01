package com.saddict.djrest.products.ui.screens.registration

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saddict.djrest.products.data.manager.PreferenceDataStore
import com.saddict.djrest.products.data.sources.ApiRepository
import com.saddict.djrest.products.model.remote.User
import com.saddict.djrest.products.model.remote.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import javax.inject.Inject

sealed interface LoginUiState {
    data class Success(val userResponse: UserResponse) : LoginUiState
    data object Error : LoginUiState
    data object Loading : LoginUiState
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: ApiRepository
) : ViewModel() {
    private val _uiState = MutableSharedFlow<LoginUiState>()
    val uiState: SharedFlow<LoginUiState> = _uiState

    //    private val repository = AppApi(context).productsRepository
//    private val encryptManager = KeyStoreManager()
    private val userPreferenceFlow = PreferenceDataStore(context)
//    abstract var encryptedToken: String

    fun login(
        username: String,
        password: String,
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    _uiState.emit(LoginUiState.Loading)
//                    val sessionManager = SessionManager(context = context)
                    val user = User(password = password, username = username)
                    val login = repository.login(user)
                    if (login.isSuccessful) {
                        val responseBody = login.body()
                        val token = responseBody!!.token
//                        val token = responseBody!!.token.encodeToByteArray()
//                        val file = File(context.filesDir, "token.txt")
//                        if(!file.exists()){
//                            file.createNewFile()
//                        }
//                        val fos = FileOutputStream(file)
//                        encryptedToken =
//                        encryptManager.encrypt(bytes = token, outputStream = fos).decodeToString()
                        userPreferenceFlow.setToken(token)
//                        sessionManager.saveAuthToken(token)
                        Log.d("Success", responseBody.toString())
                        _uiState.emit(LoginUiState.Success(responseBody))
                    } else {
                        _uiState.emit(LoginUiState.Error)
                        val errorBody = login.raw()
                        Log.e("NotSent", "Error: $errorBody")
                    }
                } catch (e: IOException) {
                    Log.e("LoginError", "Logging in error $e")
                }
            }
        }
    }
}


//class LoginViewModel : ViewModel() {
//    var uiState: LoginUiState by mutableStateOf(LoginUiState.Loading)
//    private val repository = AppApi().productsRepository
//    fun login(username: String, password: String) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                try {
//                    uiState = LoginUiState.Loading
//                    val user = User(password = password, username = username)
//                    val login = repository.login(user)
//                    if (login.isSuccessful) {
//                        val responseBody = login.body()
//                        Log.d("Success", responseBody.toString())
//                        uiState = LoginUiState.Success(responseBody!!)
//                    } else {
//                        uiState = LoginUiState.Error
//                        val errorBody = login.raw()
//                        Log.e("NotSent", "Error: $errorBody")
//                    }
//                } catch (e: IOException) {
//                    Log.e("LoginError", "Logging in error $e")
//                }
//            }
//        }
//    }
//}