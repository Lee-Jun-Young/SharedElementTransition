@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.sharedelementtransition

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Home(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val items = testData

    SharedTransitionLayout(
        modifier = modifier,
    ) {
        NavHost(
            navController = navController,
            startDestination = "list",
            modifier = Modifier.fillMaxSize(),
        ) {
            composable(
                route = "list"
            ) {
                ListScreen(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable,
                    onItemClick = { id ->
                        navController.navigate("details/${id}")
                    },
                    modifier = Modifier.fillMaxSize(),
                )
            }
            composable(
                route = "details/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backstackEntry ->
                val idx = backstackEntry.arguments?.getInt("id") ?: error(1)
                DetailScreen(
                    item = items[idx],
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedContentScope = this@composable,
                )
            }
        }
    }
}
