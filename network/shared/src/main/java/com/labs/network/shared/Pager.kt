package com.labs.network.shared

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.labs.data.ViewState
import com.labs.data.di.NetworkResponse

private const val DEFAULT_PAGE_SIZE = 20

fun <V : Any> createPager(
    enablePlaceholders: Boolean = false,
    apiCall: suspend (latestPage: Int) -> NetworkResponse<V>
): Pager<Int, V> = Pager(
    config = PagingConfig(enablePlaceholders = enablePlaceholders, pageSize = DEFAULT_PAGE_SIZE),
    pagingSourceFactory = { BasePagingSource(apiCall) }
)