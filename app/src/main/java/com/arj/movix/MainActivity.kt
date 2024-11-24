package com.arj.movix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arj.detail.ui.DetailMovieScreen
import com.arj.detail.ui.DetailMovieViewModel
import com.arj.home.ui.HomeUI
import com.arj.movix.appearance.MovixTheme
import com.arj.navigation.detail.controller.DETAIL_MOVIE_ID_ARGS
import com.arj.navigation.detail.controller.DETAIL_MOVIE_ROUTE
import com.arj.navigation.detail.controller.DETAIL_MOVIE_TITLE_ARGS
import com.arj.navigation.home.controller.HOME_ROUTE
import com.arj.search.controller.SEARCH_ROUTE
import com.arj.search.ui.SearchUI
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.arj.navigation.detail.controller.Navigation as DetailMovieNavigation
import com.arj.search.controller.Navigation as SearchNavigation

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var searchNavigation: SearchNavigation

    @Inject
    lateinit var detailMovieNavigation: DetailMovieNavigation

    private val detailMovieViewModel: DetailMovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MovixTheme {
                Surface {
                    val navController = rememberNavController()

                    NavHost(
                        navController, startDestination = HOME_ROUTE
                    ) {
                        composable(route = HOME_ROUTE) {
                            HomeUI(
                                modifier = Modifier.fillMaxSize(),
                                onSearchClicked = {
                                    searchNavigation.navigateToSearchPage(navController)
                                },
                                onItemClicked = { movieId, movieTitle ->
                                    detailMovieNavigation.navigateToDetailMoviePage(
                                        navController,
                                        movieId,
                                        movieTitle,
                                    )
                                },
                            )
                        }
                        composable(route = SEARCH_ROUTE) {
                            SearchUI(
                                onBack = { navController.popBackStack() },
                                onItemClicked = { movieId, movieTitle ->
                                    detailMovieNavigation.navigateToDetailMoviePage(
                                        navController,
                                        movieId,
                                        movieTitle,
                                    )
                                },
                            )
                        }
                        composable(
                            route = DETAIL_MOVIE_ROUTE, arguments = listOf(
                                navArgument(DETAIL_MOVIE_ID_ARGS) { type = NavType.StringType },
                                navArgument(DETAIL_MOVIE_TITLE_ARGS) { type = NavType.StringType },
                            )
                        ) { stackEntry ->
                            val movieId = stackEntry.arguments?.getString(DETAIL_MOVIE_ID_ARGS)
                            val movieTitle = stackEntry.arguments?.getString(DETAIL_MOVIE_TITLE_ARGS)
                            val movieState = detailMovieViewModel.movieState.collectAsState().value
                            val creditState = detailMovieViewModel.creditsState.collectAsState().value

                            LaunchedEffect(movieId?.isNotEmpty()) {
                                movieId?.let { id -> detailMovieViewModel.getDetailMovie(id) }
                            }

                            DetailMovieScreen(
                                title = movieTitle.orEmpty(),
                                movieState = movieState,
                                creditState = creditState,
                                movieId = movieId.orEmpty(),
                                onBack = { navController.popBackStack() },
                            )
                        }
                    }
                }
            }
        }
    }

}
