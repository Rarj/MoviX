package com.arj.movix

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.arj.uikit.R
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
                    val isConnected = connectivityViewModel.state.collectAsState().value.connectionIsConnected
                    if (isConnected != null && isConnected == false) {
                        NoConnectionAlert()
                    }

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

    @Composable
    private fun NoConnectionAlert() {
        AlertDialog(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            title = {
                Text(
                    text = "You're not connected to internet",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(resId = R.font.sono_medium)),
                )
            },
            onDismissRequest = {  },
            confirmButton = {
                Text(
                    modifier = Modifier
                        .clickable { connectivityViewModel.setOnRetry(status = true) }
                        .padding(all = 8.dp),
                    text = "Retry",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(resId = R.font.sono_medium)),
                )
            },
        )
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
