package com.cinepeek.network.shared

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.cinepeek.network.state.CinepeekNetworkResult

private const val DEFAULT_PAGE_SIZE = 20

fun <V : Any> createPager(
    enablePlaceholders: Boolean = false,
    apiCall: suspend (latestPage: Int) -> CinepeekNetworkResult<NetworkResponse<V>>
): Pager<Int, V> = Pager(
    config = PagingConfig(enablePlaceholders = enablePlaceholders, pageSize = DEFAULT_PAGE_SIZE),
    pagingSourceFactory = { BasePagingSource(apiCall) }
)