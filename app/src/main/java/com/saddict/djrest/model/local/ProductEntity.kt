package com.saddict.djrest.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.saddict.djrest.model.remote.ImageArrayResults

@Entity(tableName = "products")
data class ProductEntity(
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

