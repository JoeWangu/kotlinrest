package com.saddict.djrest.products.model.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class Order(
    @JsonProperty("results")
    val results: List<OrderResults>,
    @JsonProperty("count")
    val count: Int,
    @JsonProperty("next")
    val next: String?,
    @JsonProperty("previous")
    val previous: String?
)

data class OrderResults(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("order_date")
    val orderDate: String?,
    @JsonProperty("delivery_date")
    val deliveryDate: String?,
    @JsonProperty("status")
    val status: String?,
    @JsonProperty("customer")
    val customer: Customer?,
    @JsonProperty("product")
    val product: Products,
    @JsonProperty("quantity")
    val quantity: Int?,
)
