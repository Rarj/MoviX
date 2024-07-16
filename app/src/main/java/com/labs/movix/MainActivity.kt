package com.labs.movix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.labs.home.ui.HomeUI
import com.labs.navigation.home.controller.Navigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface {
                val navController = rememberNavController()

                NavHost(
                    navController, startDestination = "homey"
                ) {
                    composable(route = "homey") {
                        HomeUI(
                            onSearchClicked = { println("CLICKED_EVENT: SEARCH") },
                            onFilterClicked = { println("CLICKED_EVENT: FILTER") },
                        ) {

                        }
                    }
                }
            }
        }
    }

}
