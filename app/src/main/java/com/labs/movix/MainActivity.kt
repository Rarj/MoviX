package com.labs.movix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.labs.home.ui.HomeUI
import com.labs.navigation.home.controller.HOME_ROUTE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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
                            onSearchClicked = { println("CLICKED_EVENT: SEARCH") },
                            onFilterClicked = { println("CLICKED_EVENT: FILTER") },
                            onItemClicked = { movieId -> println("CLICKED_EVENT: MOVIE_CLICKED : $movieId") },
                        )
                    }
                }
            }
        }
    }

}
