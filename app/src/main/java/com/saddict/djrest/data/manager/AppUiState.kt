package com.saddict.djrest.data.manager

sealed interface AppUiState{
    data object Success : AppUiState
    data object Error : AppUiState
    data object Loading : AppUiState
}