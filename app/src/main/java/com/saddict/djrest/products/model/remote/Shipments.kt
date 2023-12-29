package com.saddict.djrest.products.model.remote

import com.fasterxml.jackson.annotation.JsonProperty

data class Shipments(
    @JsonProperty("results")
    val results: List<ShipmentsResults>,
    @JsonProperty("count")
    val count: Int,
    @JsonProperty("next")
    val next: String?,
    @JsonProperty("previous")
    val previous: String?
)

data class ShipmentsResults(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("shipment_date")
    val shipmentDate: String?,
    @JsonProperty("tracking_number")
    val trackingNumber: Int?,
    @JsonProperty("recipient_information")
    val recipientInformation: String?,
)