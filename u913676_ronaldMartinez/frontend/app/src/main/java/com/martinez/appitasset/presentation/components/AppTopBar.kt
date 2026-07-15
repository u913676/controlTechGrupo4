package com.martinez.appitasset.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.martinez.appitasset.presentation.navigation.NavRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(navHostController: NavHostController){
    val navBackStackEntry = navHostController.currentBackStackEntryAsState()
    val route = navBackStackEntry.value?.destination?.route

    println("route: $route")

    val title = NavRoutes.getTitle(route)
    val showButton = route != NavRoutes.HOME

    CenterAlignedTopAppBar(
        title = { Text(
            title,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00BCD4)
        ) },
        navigationIcon = {
            if (showButton) {
                IconButton(
                    onClick = {navHostController.popBackStack()},
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor =  Color(0xFF00BCD4))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "")
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF0E0D0D)
        )
    )
}