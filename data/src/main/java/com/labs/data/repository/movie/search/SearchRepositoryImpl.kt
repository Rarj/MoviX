package com.labs.data.repository.movie.search

import androidx.paging.PagingData
import com.labs.data.ViewState
import com.labs.data.di.ApiService
import com.labs.data.pagination.createPager
import com.labs.data.repository.genre.Genre
import com.labs.data.repository.movie.Movie
import com.labs.data.repository.movie.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SearchRepository {

    override suspend fun searchMovie(keyword: String, selectedGenre: Genre): Flow<PagingData<Movie>> {
        return createPager {  page ->
            apiService.searchMovie(keyword, page)
        }.flow.flowOn(Dispatchers.IO)
    }
}