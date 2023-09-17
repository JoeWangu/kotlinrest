package com.saddict.djrest.model.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class Transaction(
    @JsonProperty("results")
    val results: List<TransactionResults>,
    @JsonProperty("count")
    val count: Int,
    @JsonProperty("next")
    val next: String?,
    @JsonProperty("previous")
    val previous: String?
)

data class TransactionResults(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("transaction_type")
    val transactionType: String?,
    @JsonProperty("transaction_date")
    val transactionDate: String?,
    @JsonProperty("product")
    val product: Products,
    @JsonProperty("quantity")
    val quantity: Int?,
)
