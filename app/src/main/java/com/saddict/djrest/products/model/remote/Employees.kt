package com.saddict.djrest.products.model.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class Employees(
    @JsonProperty("results")
    val results: List<EmployeesResults>,
    @JsonProperty("count")
    val count: Int,
    @JsonProperty("next")
    val next: String?,
    @JsonProperty("previous")
    val previous: String?
)

data class EmployeesResults(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("address")
    val address: String?,
    @JsonProperty("contact_info")
    val contactInfo: String?,
)