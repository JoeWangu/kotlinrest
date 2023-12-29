package com.saddict.djrest.products.ui.screens.registration

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saddict.djrest.products.data.manager.PreferenceDataStore
import com.saddict.djrest.products.data.sources.ApiRepository
import com.saddict.djrest.products.model.remote.RegisterUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import javax.inject.Inject

//ToDo
// 1.When registering catch duplicate user error and send appropriate response

@HiltViewModel
class RegisterViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: ApiRepository
) : ViewModel() {
    private val _uiState = MutableSharedFlow<com.saddict.djrest.products.data.manager.AppUiState>()
    val uiState: SharedFlow<com.saddict.djrest.products.data.manager.AppUiState> = _uiState

    //    private val repository = AppApi(context).productsRepository
    private val userPreferenceFlow = PreferenceDataStore(context)

    fun register(
        username: String,
        password: String,
        email: String
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    _uiState.emit(com.saddict.djrest.products.data.manager.AppUiState.Loading)
                    val user = RegisterUser(
                        password = password, username = username, email = email
                    )
                    val register = repository.register(user)
                    if (register.isSuccessful) {
                        val responseBody = register.body()
                        val token = responseBody!!.token
                        userPreferenceFlow.setToken(token)
                        Log.d("Success", responseBody.toString())
                        _uiState.emit(com.saddict.djrest.products.data.manager.AppUiState.Success)
                    } else {
                        _uiState.emit(com.saddict.djrest.products.data.manager.AppUiState.Error)
                        val errorBody = register.raw()
                        Log.e("NotSent", "Error: $errorBody")
                    }
                } catch (e: IOException) {
                    Log.e("RegisterError", "Logging in error $e")
                }
            }
        }
    }
}