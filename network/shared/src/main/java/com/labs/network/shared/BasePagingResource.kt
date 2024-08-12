package com.labs.network.shared

import androidx.paging.PagingSource
import androidx.paging.PagingState

class BasePagingSource<V : Any>(
    private val apiCall: suspend (latestPage: Int) -> NetworkResponse<V>,
) : PagingSource<Int, V>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, V> {
        val page = params.key ?: 1
        return try {
            val response = apiCall.invoke(page)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (
                    response.totalPages != null && page == response.totalPages ||
                    response.totalPages == 0
                ) null
                else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, V>): Int? {
        return null
    }
}