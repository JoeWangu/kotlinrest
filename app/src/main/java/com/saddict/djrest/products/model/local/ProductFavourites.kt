package com.saddict.djrest.products.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductFavourites(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "product_name")
    val productName: String?,
    @ColumnInfo(name = "model_number")
    val modelNumber: String?,
    val specifications: String?,
    val price: Double?,
    val image: Int?,
    @ColumnInfo(name = "image_detail")
    val imageDetail: String?,
    val category: Int?,
    val supplier: Int?,
)
