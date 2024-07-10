package com.labs.home.impl.discover

import androidx.paging.PagingData
import com.labs.data.repository.genre.Genre
import com.labs.home.api.HomeService
import com.labs.home.api.response.discover.Movie
import com.labs.network.shared.createPager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: HomeService
) : MovieRepository {

    private var genre: Genre? = null

    override fun getSelectedGenre(): Genre? = this.genre

    override fun setSelectedGenre(genre: Genre?) {
        this.genre = genre
    }

    override suspend fun getDiscoverMovie(): Flow<PagingData<Movie>> {
        return createPager { page ->
            val ids = listOf(genre?.id ?: "").toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "%7C")
            apiService.getDiscoverMovie(ids, page)
        }.flow.flowOn(Dispatchers.IO)
    }

}