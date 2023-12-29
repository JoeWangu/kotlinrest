package com.saddict.djrest.utils

import com.saddict.djrest.products.model.local.ProductEntity
import com.saddict.djrest.products.model.local.ProductFavourites
import com.saddict.djrest.products.model.remote.ImageArrayResults
import com.saddict.djrest.products.model.remote.ProductsResult

class DataMapper {
    companion object {
        fun ProductsResult.mapToEntity(): ProductEntity {
            return ProductEntity(
                id = id,
                productName = productName,
                modelNumber = modelNumber,
                specifications = specifications,
                price = price,
                image = image,
                imageDetail = imageDetail.image,
                category = category,
                supplier = supplier
            )
        }

        fun ProductEntity.mapToResults(): ProductsResult {
            return ProductsResult(
                id = id,
                productName = productName,
                modelNumber = modelNumber,
                specifications = specifications,
                price = price,
                image = image,
                imageDetail = ImageArrayResults(id = image!!, image = imageDetail),
                category = category,
                supplier = supplier
            )
        }

        fun ProductsResult.mapToFavourites(): ProductFavourites {
            return ProductFavourites(
                id = id,
                productName = productName,
                modelNumber = modelNumber,
                specifications = specifications,
                price = price,
                image = image,
                imageDetail = imageDetail.image,
                category = category,
                supplier = supplier
            )
        }
    }
}