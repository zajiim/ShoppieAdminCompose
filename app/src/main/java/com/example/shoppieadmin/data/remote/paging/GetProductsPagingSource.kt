package com.example.shoppieadmin.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.shoppieadmin.data.remote.api.ShoppieApi
import com.example.shoppieadmin.domain.home.models.Product
import com.example.shoppieadmin.domain.main.datamanager.LocalUserManager
import kotlinx.coroutines.flow.first


class GetProductsPagingSource(
    private val shoppieApi: ShoppieApi,
    private val localUserManager: LocalUserManager
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val page = params.key ?: 1
            val token = localUserManager.readAppToken().first() ?: throw Exception("token not fetched")
            val response = shoppieApi.getAllProducts(token, page, params.loadSize)

            LoadResult.Page(
                data = response.products,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page == response.totalPages) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}