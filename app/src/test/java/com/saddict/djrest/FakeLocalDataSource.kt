package com.saddict.djrest

import com.saddict.djrest.model.local.ProductEntity

object FakeLocalDataSource {
    private const val idOne = 1
    private const val productNameOne = "sku1"
    private const val modelNumberOne = "barcode1"
    private const val specificationsOne = "title1"
    private const val priceOne = 20000.0
    private const val imageOne = 1
    private const val imageUrlOne = "imageUrlOne"
    private const val categoryOne = 1
    private const val supplierOne = 1

    private const val idTwo = 2
    private const val productNameTwo = "sku2"
    private const val modelNumberTwo = "barcode2"
    private const val specificationsTwo = "title2"
    private const val priceTwo = 10000.0
    private const val imageTwo = 2
    private const val imageUrlTwo = "imageUrlTwo"
    private const val categoryTwo = 2
    private const val supplierTwo = 2

    val productEntity = listOf(
        ProductEntity(
            id = idOne,
            productName = productNameOne,
            modelNumber = modelNumberOne,
            specifications = specificationsOne,
            price = priceOne,
            image = imageOne,
            imageDetail = imageUrlOne,
            category = categoryOne,
            supplier = supplierOne
        ),
        ProductEntity(
            id = idTwo,
            productName = productNameTwo,
            modelNumber = modelNumberTwo,
            specifications = specificationsTwo,
            price = priceTwo,
            image = imageTwo,
            imageDetail = imageUrlTwo,
            category = categoryTwo,
            supplier = supplierTwo
        )
    )
}