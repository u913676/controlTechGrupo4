package com.martinez.appitasset.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

import androidx.navigation.compose.rememberNavController
import com.martinez.appitasset.presentation.navigation.BottonNavItem

@Composable
fun AppButtonBar(navHostController: NavHostController) {

    val options =  listOf(
        BottonNavItem.home,
        BottonNavItem.itAssets,
        BottonNavItem.register,
        BottonNavItem.history,
        BottonNavItem.profile,
    )

    NavigationBar(
        containerColor = Color(0xFFF000000)
    ) {
        val routeSelect = navHostController.currentBackStackEntryAsState()
            .value?.destination?.route

        options.forEach { item ->
            NavigationBarItem(
                selected = routeSelect == item.route,
                onClick = { navHostController.navigate(item.route) },
                icon = { Icon(
                    item.icon,
                    contentDescription = item.title ) },
                label = { Text(item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF3F51B5),
                    indicatorColor =  Color(0xFFFFFFFF),
                    selectedTextColor =  Color(0xFFFFFFFF),
                    unselectedIconColor = Color(0xFFFFFFFF),
                    unselectedTextColor = Color(0xFFFFFFFF),
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppButtonBarPreview() {
    val navController = rememberNavController()
    AppButtonBar(navController)
}