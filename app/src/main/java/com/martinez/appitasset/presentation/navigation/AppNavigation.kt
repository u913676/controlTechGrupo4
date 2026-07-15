package com.martinez.appitasset.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.martinez.appitasset.di.AppContainer
import com.martinez.appitasset.presentation.screens.edit.EditScreen
import com.martinez.appitasset.presentation.screens.home.HomeScreen
import com.martinez.appitasset.presentation.screens.add.AddScreen
import com.martinez.appitasset.presentation.screens.register.RegisterScreen
import com.martinez.appitasset.presentation.screens.search.SearchScreen


@Composable
fun AppNavigation(
    navHostController: NavHostController,
    container: AppContainer,
    paddingValues: PaddingValues
) {

    NavHost(
        navController = navHostController,
        startDestination = NavRoutes.HOME,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(NavRoutes.HOME) {
            //HomeScreen()
        }
        composable(NavRoutes.ITASSETS) {
            //AddScreen(container)
        }
        composable(NavRoutes.REGISTER) {
            RegisterScreen(container)
        }
        composable(NavRoutes.HISTORY) {
            //SearchScreen(container)
        }
        composable(NavRoutes.PROFILE) {
            //SearchScreen(container)
        }

    }
}