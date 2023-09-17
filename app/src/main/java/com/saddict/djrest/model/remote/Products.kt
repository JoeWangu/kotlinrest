package com.saddict.djrest.model.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class Products(
    @JsonProperty("results")
    val results: List<ProductsResult>,
    @JsonProperty("count")
    val count: Int,
    @JsonProperty("next")
    val next: String?,
    @JsonProperty("previous")
    val previous: String?
)

data class ProductsResult(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("name")
    val productName: String?,
    @JsonProperty("model_number")
    val modelNumber: String?,
    @JsonProperty("specifications")
    val specifications: String?,
    @JsonProperty("price")
    val price: Double?,
    @JsonProperty("image")
    val image: Int?,
    @JsonProperty("image_detail")
    val imageDetail: ImageArrayResults,
    @JsonProperty("category")
    val category: Int?,
    @JsonProperty("supplier")
    val supplier: Int?,
)

data class ImageArrayResults(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("image")
    val image: String?,
)

data class PostProducts(
    val productName: String,
    val modelNumber: String,
    val specifications: String,
    val price: Int,
    val image: Int,
    val category: Int,
    val supplier: Int,
)