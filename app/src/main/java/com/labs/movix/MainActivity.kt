package com.labs.movix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.labs.home.ui.HomeUI
import com.labs.navigation.home.controller.HOME_ROUTE
import com.labs.search.controller.Navigation
import com.labs.search.controller.SEARCH_ROUTE
import com.labs.search.ui.SearchUI
import com.labs.search.ui.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var searchNavigation: Navigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
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
                            onFilterClicked = { println("CLICKED_EVENT: FILTER") },
                            onItemClicked = { movieId -> println("CLICKED_EVENT: MOVIE_CLICKED : $movieId") },
                        )
                    }
                    composable(route = SEARCH_ROUTE) {
                        val viewModel: SearchViewModel by viewModels()
                        SearchUI(
                            onBack = { navController.popBackStack() },
                            searchState = viewModel.state.collectAsState().value,
                            onUpdateKeyword = { keyword -> viewModel.onUpdateKeywordNew(keyword) },
                            onItemClicked = { movieId -> },
                        )
                    }
                }
            }
        }
    }

}
