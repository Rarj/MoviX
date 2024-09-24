package com.arj.movix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arj.detail.ui.DetailMovieScreen
import com.arj.home.ui.HomeUI
import com.arj.movix.appearance.MovixTheme
import com.arj.navigation.detail.controller.DETAIL_MOVIE_ID_ARGS
import com.arj.navigation.detail.controller.DETAIL_MOVIE_ROUTE
import com.arj.navigation.home.controller.HOME_ROUTE
import com.arj.network.ConnectivityManagerViewModel
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

    private val connectivityViewModel: ConnectivityManagerViewModel by viewModels()

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
                                onItemClicked = { movieId ->
                                    detailMovieNavigation.navigateToDetailMoviePage(
                                        navController,
                                        movieId,
                                    )
                                },
                            )
                        }
                        composable(route = SEARCH_ROUTE) {
                            SearchUI(
                                onBack = { navController.popBackStack() },
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

    override fun onStart() {
        super.onStart()

        connectivityViewModel.apply {
            register()
            getInitializedConnection()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        connectivityViewModel.clearInstance()
    }

}
