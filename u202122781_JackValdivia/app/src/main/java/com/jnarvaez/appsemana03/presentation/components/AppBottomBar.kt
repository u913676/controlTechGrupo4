package com.jnarvaez.appsemana03.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jnarvaez.appsemana03.presentation.navigation.BotonNavItem

@Composable
fun AppBottomBar(navHostController: NavHostController) {
    val opciones = listOf(BotonNavItem.Equipos, BotonNavItem.Historial)

    NavigationBar(
        containerColor = Color(0xFF595959)
    ) {
        val rutaSel = navHostController.currentBackStackEntryAsState().value?.destination?.route
        opciones.forEach { item ->
            NavigationBarItem(
                selected = rutaSel == item.ruta,
                onClick = { navHostController.navigate(item.ruta) },
                icon = { Icon(item.icono, "") },
                label = { Text(item.titulo) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFFFFFFF),
                    indicatorColor = Color(0xFF1A237E),
                    selectedTextColor = Color(0xFFFFFFFF),
                    unselectedIconColor = Color(0xFFE7E7E7),
                    unselectedTextColor = Color(0xFFE7E7E7)
                )
            )
        }
    }
}
