package com.arj.network.shared

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.net.UnknownHostException

class BasePagingSource<V : Any>(
    private val apiCall: suspend (latestPage: Int) -> com.arj.network.state.MovixNetworkResult<NetworkResponse<V>>,
) : PagingSource<Int, V>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, V> {
        val page = params.key ?: 1
        return try {
            return when (val response = apiCall.invoke(page)) {
                is com.arj.network.state.MovixNetworkResult.Success -> {
                    LoadResult.Page(
                        data = response.value.results,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (
                            response.value.totalPages != null &&
                            page == response.value.totalPages ||
                            response.value.totalPages == 0
                        ) null
                        else page + 1
                    )
                }

                is com.arj.network.state.MovixNetworkResult.Failed -> LoadResult.Error(Exception("Offline"))
                else -> LoadResult.Error(Exception("Uncaught Exception!"))
            }
        } catch (e: UnknownHostException) {
            LoadResult.Error(Exception("Offline"))
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, V>): Int? {
        return state.anchorPosition
    }
}
