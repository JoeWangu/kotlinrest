package com.saddict.djrest.utils

import androidx.room.TypeConverter
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.saddict.djrest.products.model.remote.ImageArrayResults

class Converters {
    private val objectMapper = ObjectMapper()

    @TypeConverter
    fun fromString(value: String): List<ImageArrayResults> {
        return objectMapper.readValue(value, object : TypeReference<List<ImageArrayResults>>() {})
    }

    @TypeConverter
    fun fromList(list: List<ImageArrayResults>): String {
        return objectMapper.writeValueAsString(list)
    }
}
