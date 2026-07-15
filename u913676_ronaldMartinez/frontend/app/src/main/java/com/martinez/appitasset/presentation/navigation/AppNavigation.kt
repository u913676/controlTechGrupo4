package com.martinez.appitasset.presentation.navigation

import android.util.Log
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
import com.martinez.appitasset.presentation.screens.list.ListScreen
import com.martinez.appitasset.presentation.screens.register.RegisterScreen
import com.martinez.appitasset.presentation.screens.search.SearchScreen
import com.martinez.appitasset.presentation.screens.view.ViewScreen


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
            ListScreen(container, navHostController)
        }
        composable(NavRoutes.REGISTER) {
            RegisterScreen(container, navHostController)
        }
        composable(NavRoutes.HISTORY) {
            //SearchScreen(container)
        }
        composable(NavRoutes.PROFILE) {
            //SearchScreen(container)
        }

        composable("${NavRoutes.VIEW}/{id}"){ entry->
            val id = entry.arguments?.getString("id")?:""
            Log.d("ITAsset", id)
            ViewScreen(container,navHostController,id)
        }

    }
}