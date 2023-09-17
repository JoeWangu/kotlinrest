package com.saddict.djrest.model.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class Inventory(
    @JsonProperty("results")
    val results: List<InventoryResults>,
    @JsonProperty("count")
    val count: Int,
    @JsonProperty("next")
    val next: String?,
    @JsonProperty("previous")
    val previous: String?
)

data class InventoryResults(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("product")
    val product: Products,
    @JsonProperty("last_sales_date")
    val lastSalesDate: String?,
    @JsonProperty("quantity_sold")
    val quantitySold: Int?,
    @JsonProperty("sales")
    val sales: String?,
    @JsonProperty("quantity_in_stock")
    val quantityInStock: Int?,
    @JsonProperty("minimum_quantity")
    val minimumQuantity: Int?,
)