package com.saddict.djrest.products.ui.screens.favourites

import androidx.lifecycle.ViewModel
import com.saddict.djrest.products.data.manager.usecases.GetAllFavouritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    useCase: GetAllFavouritesUseCase
) : ViewModel() {

}