package com.saddict.djrest.products.model.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class Location(
    @JsonProperty("results")
    val results: List<LocationResults>,
    @JsonProperty("count")
    val count: Int,
    @JsonProperty("next")
    val next: String?,
    @JsonProperty("previous")
    val previous: String?
)

data class LocationResults(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("address")
    val address: String?,
    @JsonProperty("capacity")
    val capacity: Int?,
    @JsonProperty("contact_information")
    val contactInformation: String?,
)