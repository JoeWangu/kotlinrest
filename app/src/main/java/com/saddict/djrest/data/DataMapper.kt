package com.saddict.djrest.data

import com.saddict.djrest.model.local.ProductEntity
import com.saddict.djrest.model.remote.ProductsResult

class DataMapper {
    companion object{
        fun mapToEntity(productsResult: ProductsResult): ProductEntity {
            return ProductEntity(
                id = productsResult.id,
                productName = productsResult.productName,
                modelNumber = productsResult.modelNumber,
                specifications = productsResult.specifications,
                price = productsResult.price,
                image = productsResult.image,
                imageDetail = productsResult.imageDetail.image,
                category = productsResult.category,
                supplier = productsResult.supplier
            )
        }
    }
}