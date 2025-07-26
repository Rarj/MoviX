package com.arj.movix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arj.movix.appearance.MovixTheme
import com.arj.search.ui.SearchUI
import com.cinepeek.detail.ui.DetailMovieScreen
import com.cinepeek.detail.ui.DetailMovieViewModel
import com.cinepeek.home.ui.HomeUI
import com.cinepeek.navigation.detail.controller.DETAIL_MOVIE_ID_ARGS
import com.cinepeek.navigation.detail.controller.DETAIL_MOVIE_ROUTE
import com.cinepeek.navigation.detail.controller.DETAIL_MOVIE_TITLE_ARGS
import com.cinepeek.navigation.home.controller.HOME_ROUTE
import com.cinepeek.search.controller.SEARCH_ROUTE
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.cinepeek.navigation.detail.controller.Navigation as DetailMovieNavigation
import com.cinepeek.search.controller.Navigation as SearchNavigation

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
                                onRetry = {
                                    movieId?.let { id -> detailMovieViewModel.getDetailMovie(id) }
                                },
                            )
                        }
                    }
                }
            }
        }
    }

}
