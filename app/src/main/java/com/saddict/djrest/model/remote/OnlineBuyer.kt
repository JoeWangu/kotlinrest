package com.saddict.djrest.model.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class OnlineBuyer(
    @JsonProperty("results")
    val results: List<OnlineBuyerResults>,
    @JsonProperty("count")
    val count: Int,
    @JsonProperty("next")
    val next: String?,
    @JsonProperty("previous")
    val previous: String?
)

data class OnlineBuyerResults(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("user")
    val user: String?,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("address")
    val address: String?,
    @JsonProperty("created_date")
    val createdDate: String?,
)