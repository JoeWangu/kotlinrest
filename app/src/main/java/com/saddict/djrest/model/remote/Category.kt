package com.saddict.djrest.model.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class Category(
    @JsonProperty("results")
    val results: List<CategoryResults>,
    @JsonProperty("count")
    val count: Int,
    @JsonProperty("next")
    val next: String?,
    @JsonProperty("previous")
    val previous: String?
)

data class CategoryResults(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("description")
    val description: String?
)