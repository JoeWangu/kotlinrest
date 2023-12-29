package com.saddict.djrest.products.model.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class Customer(
    @JsonProperty("results")
    val results: List<CustomerResults>,
    @JsonProperty("count")
    val count: Int,
    @JsonProperty("next")
    val next: String?,
    @JsonProperty("previous")
    val previous: String?
)

data class CustomerResults(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("contact_details ")
    val contactDetails : String?,
    @JsonProperty("address")
    val address: String?,
    @JsonProperty("extra_info")
    val extraInfo: String?,
)
