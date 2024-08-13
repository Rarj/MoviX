package com.labs.movix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.labs.detail.ui.DetailMovieScreen
import com.labs.home.impl.discover.mapper.DiscoverMovie
import com.labs.home.ui.HomeUI
import com.labs.home.ui.HomeViewModel
import com.labs.navigation.detail.controller.DETAIL_MOVIE_ID_ARGS
import com.labs.navigation.detail.controller.DETAIL_MOVIE_ROUTE
import com.labs.navigation.home.controller.HOME_ROUTE
import com.labs.search.controller.SEARCH_ROUTE
import com.labs.search.ui.SearchUI
import com.labs.search.ui.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.labs.navigation.detail.controller.Navigation as DetailMovieNavigation
import com.labs.search.controller.Navigation as SearchNavigation

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var searchNavigation: SearchNavigation

    @Inject
    lateinit var detailMovieNavigation: DetailMovieNavigation

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var pagingItems: LazyPagingItems<DiscoverMovie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            Surface {
                val navController = rememberNavController()

                NavHost(
                    navController, startDestination = HOME_ROUTE
                ) {
                    composable(route = HOME_ROUTE) {
                        pagingItems = homeViewModel.moviePagingDataState.collectAsLazyPagingItems()

                        HomeUI(
                            modifier = Modifier.fillMaxSize(),
                            pagingItems = pagingItems,
                            state = homeViewModel.state.collectAsState().value,
                            onSearchClicked = {
                                searchNavigation.navigateToSearchPage(navController)
                            },
                            onItemClicked = { movieId ->
                                detailMovieNavigation.navigateToDetailMoviePage(
                                    navController,
                                    movieId,
                                )
                            },
                            onFilterRefreshed = { genre ->
                                lifecycleScope.launch {
                                    homeViewModel.setSelectedGenre(genre.id, genre.name)
                                    pagingItems.refresh()
                                }
                            }
                        )
                    }
                    composable(route = SEARCH_ROUTE) {
                        val viewModel: SearchViewModel by viewModels()
                        SearchUI(
                            onBack = { navController.popBackStack() },
                            searchState = viewModel.state.collectAsState().value,
                            onUpdateKeyword = { keyword -> viewModel.onUpdateKeywordNew(keyword) },
                            onItemClicked = { movieId ->
                                detailMovieNavigation.navigateToDetailMoviePage(
                                    navController,
                                    movieId,
                                )
                            },
                        )
                    }
                    composable(
                        route = DETAIL_MOVIE_ROUTE,
                        arguments = listOf(navArgument(DETAIL_MOVIE_ID_ARGS) {
                            type = NavType.StringType
                        })
                    ) { backstackEntry ->
                        val movieId = backstackEntry.arguments?.getString(DETAIL_MOVIE_ID_ARGS)

                        DetailMovieScreen(
                            movieId = movieId.orEmpty(),
                            onBack = { navController.popBackStack() },
                        )
                    }
                }
            }
        }
    }

}
