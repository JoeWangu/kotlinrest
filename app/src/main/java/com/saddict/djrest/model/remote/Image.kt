package com.saddict.djrest.model.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

//@Entity(tableName = "image")
data class Image(
//    @PrimaryKey(autoGenerate = false)
    @JsonProperty("results")
    val results: List<ImageResults>,
    @JsonProperty("count")
    val count: Int,
    @JsonProperty("next")
    val next: String?,
    @JsonProperty("previous")
    val previous: String?
)

//@Entity(tableName = "image_result")
data class ImageResults(
//    @PrimaryKey(autoGenerate = false)
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("image")
    val image: String?,
)