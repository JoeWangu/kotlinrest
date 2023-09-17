package com.saddict.djrest

import com.saddict.djrest.data.sources.remote.OnlineAppRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkProductRepositoryTest {
    @Test
    fun networkProductRepository_getProducts_verifyProductList() = runTest{
        val repository = OnlineAppRepository(productsApiService = FakeProductsApiService())
        assertEquals(repository.getProducts(), FakeDataSource.products)
    }
}
