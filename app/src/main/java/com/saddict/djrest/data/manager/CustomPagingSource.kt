package com.saddict.djrest.data.manager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.saddict.djrest.data.sources.ApiRepository
import com.saddict.djrest.model.local.ProductEntity
import com.saddict.djrest.model.remote.ProductsResult
import com.saddict.djrest.utils.DataMapper.Companion.mapToEntity
import javax.inject.Inject

private const val INITIAL_PAGE = 1
class CustomPagingSource @Inject constructor(
    private val productApi: ApiRepository,
): PagingSource<Int, ProductEntity>() {
    override fun getRefreshKey(state: PagingState<Int, ProductEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductEntity> {
        return try {
            val page = params.key ?: INITIAL_PAGE
            val response = productApi.getProducts(page).results
            val entities = response.map { it.mapToEntity() }
            LoadResult.Page(
                data = entities,
                prevKey = if (page == INITIAL_PAGE) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}