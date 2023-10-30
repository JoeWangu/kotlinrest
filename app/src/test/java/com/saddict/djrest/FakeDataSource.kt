package com.saddict.djrest

//import com.saddict.djrest.model.remote.ImageArrayResults
//import com.saddict.djrest.model.remote.Products
//import com.saddict.djrest.model.remote.ProductsResult
//
//object FakeDataSource {
//    private const val idOne = 1
//    private const val productNameOne = "sku1"
//    private const val modelNumberOne = "barcode1"
//    private const val specificationsOne = "title1"
//    private const val priceOne = 20000.0
//    private const val imageOne = 1
//    private const val categoryOne = 1
//    private const val supplierOne = 1
//
//    private const val idTwo = 2
//    private const val productNameTwo = "sku2"
//    private const val modelNumberTwo = "barcode2"
//    private const val specificationsTwo = "title2"
//    private const val priceTwo = 10000.0
//    private const val imageTwo = 2
//    private const val categoryTwo = 2
//    private const val supplierTwo = 2
//
//    private val imageResults = ImageArrayResults(
//        id = 1,
//        image = "null"
//    )
//
//    private val imageDetailOne = imageResults
//    private val imageDetailTwo = imageResults
//
//    private val productsResultList = listOf(
//        ProductsResult(
//            id = idOne,
//            productName = productNameOne,
//            modelNumber = modelNumberOne,
//            specifications = specificationsOne,
//            price = priceOne,
//            image = imageOne,
//            imageDetail = imageDetailOne,
//            category = categoryOne,
//            supplier = supplierOne
//        ),
//        ProductsResult(
//            id = idTwo,
//            productName = productNameTwo,
//            modelNumber = modelNumberTwo,
//            specifications = specificationsTwo,
//            price = priceTwo,
//            image = imageTwo,
//            imageDetail = imageDetailTwo,
//            category = categoryTwo,
//            supplier = supplierTwo
//        )
//    )
//
//    val products = Products(
//        results = productsResultList,
//        count = 1,
//        next = "1",
//        previous = "1"
//    )
//}