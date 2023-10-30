package com.saddict.djrest.data.manager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.saddict.djrest.model.remote.ProductsResult
import com.saddict.djrest.network.ProductsApiService

class ProductPagingSource(private val productsApiService: ProductsApiService)
    :PagingSource<Int, ProductsResult>() {
    override fun getRefreshKey(state: PagingState<Int, ProductsResult>): Int? {
        return state.anchorPosition?.let {anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductsResult> {
        return try {
            val page = params.key ?: 1
            val response = productsApiService.getProducts(format = "json", page = page)

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page.plus(1)
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}