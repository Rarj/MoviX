package com.arj.movix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arj.detail.ui.DetailMovieScreen
import com.arj.detail.ui.DetailMovieViewModel
import com.arj.filter.controller.FilterState
import com.arj.filter.controller.Navigation
import com.arj.genre.ui.FilterScreen
import com.arj.home.ui.AlertAboutUI
import com.arj.home.ui.HomeUI
import com.arj.movix.appearance.MovixTheme
import com.arj.movix.navigation.BottomNavItem
import com.arj.navigation.detail.controller.DETAIL_MOVIE_ID_ARGS
import com.arj.navigation.detail.controller.DETAIL_MOVIE_ROUTE
import com.arj.navigation.detail.controller.DETAIL_MOVIE_TITLE_ARGS
import com.arj.navigation.home.controller.HOME_ROUTE
import com.arj.search.controller.SEARCH_ROUTE
import com.arj.search.ui.SearchUI
import com.arj.uikit.ToolbarUiKit
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

    @Inject
    lateinit var filterNavigation: Navigation


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovixTheme {
                Surface {
                    val filterState = filterNavigation.state.collectAsState()
                    val isAboutClicked = remember { mutableStateOf(false) }

                    val navController = rememberNavController()

                    if (filterState.value is FilterState.VISIBLE) {
                        FilterScreen(
//                            selectedGenre = state.selectedGenreId.orEmpty(),
                            selectedGenre = "28",
//                            onDismiss = { filterPageState.value = !filterPageState.value },
                            onDismiss = { filterNavigation.hideBottomSheet() },
                            onGenreClicked = { genre ->
//                                filterPageState.value = !filterPageState.value
//
//                                viewModel.apply {
//                                    setSelectedGenre(genre?.id, genre?.name)
//                                    getMovies()
//                                }
                            })
                    }

                    Scaffold(
                        topBar = {
                            ToolbarUiKit(
                                onSearchClicked = {
                                    navController.popBackStack()
                                    searchNavigation.navigateToSearchPage(navController)
                                },
                                onFilterClicked = {
                                    filterNavigation.showBottomSheet()
                                },
                                onAboutClicked = { isAboutClicked.value = !isAboutClicked.value },
                                scrollBehavior = null,
                            )
                        },
                        bottomBar = {
                            val bottomNavigationItems = listOf(
                                BottomNavItem.Home,
                                BottomNavItem.NowPlaying,
                            )
                            val navBackStackEntry = navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry.value?.destination?.route

                            AnimatedVisibility(
                                visible = currentRoute == HOME_ROUTE || currentRoute == SEARCH_ROUTE,
                                enter = slideInVertically(initialOffsetY = { it }),
                                exit = slideOutVertically(targetOffsetY = { it }),
                            ) {
                                NavigationBar {
                                    bottomNavigationItems.forEach { item ->
                                        NavigationBarItem(
                                            selected = currentRoute == item.route,
                                            onClick = {
                                                navController.navigate(item.route) {
                                                    popUpTo(navController.graph.startDestinationId)
                                                    launchSingleTop = true
                                                }
                                            },
                                            icon = { Icon(item.icon, contentDescription = null) },
                                            label = { Text(item.label) })
                                    }
                                }
                            }
                        },
                    ) { innerPadding ->
                        if (isAboutClicked.value) AlertAboutUI(isAboutClicked)

                        NavHost(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController,
                            startDestination = HOME_ROUTE,
                        ) {
                            composable(route = HOME_ROUTE) {
                                HomeUI(
                                    onSearchClicked = {
                                        navController.popBackStack()
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
                                    navArgument(DETAIL_MOVIE_TITLE_ARGS) {
                                        type = NavType.StringType
                                    },
                                )
                            ) { stackEntry ->
                                val movieId = stackEntry.arguments?.getString(DETAIL_MOVIE_ID_ARGS)
                                val movieTitle =
                                    stackEntry.arguments?.getString(DETAIL_MOVIE_TITLE_ARGS)
                                val movieState =
                                    detailMovieViewModel.movieState.collectAsState().value
                                val creditState =
                                    detailMovieViewModel.creditsState.collectAsState().value

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

}
