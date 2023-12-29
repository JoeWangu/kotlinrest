package com.saddict.djrest.products.model.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class Supplier(
    @JsonProperty("results")
    val results: List<SupplierResults>,
    @JsonProperty("count")
    val count: Int,
    @JsonProperty("next")
    val next: String?,
    @JsonProperty("previous")
    val previous: String?
)

data class SupplierResults(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("contact_details")
    val contactDetails: String?,
    @JsonProperty("address")
    val address: String?
)