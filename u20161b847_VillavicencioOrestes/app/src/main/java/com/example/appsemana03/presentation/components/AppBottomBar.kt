package com.example.appsemana03.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.appsemana03.core.navigation.BotonNavItem

@Composable
fun AppBottomBar(navHostController: NavHostController) {
    val opciones = listOf(
        BotonNavItem.Inicio,
        BotonNavItem.Listar,
        BotonNavItem.Buscar,
        BotonNavItem.Asignar,
        BotonNavItem.Historial
    )

    NavigationBar(
        containerColor = Color(0xFF4F8FEF),
        tonalElevation = 8.dp
    ) {
        val rutaSel = navHostController
            .currentBackStackEntryAsState()
            .value
            ?.destination
            ?.route

        opciones.forEach { item ->
            val seleccionado = rutaSel == item.ruta

            NavigationBarItem(
                selected = seleccionado,
                onClick = {
                    navHostController.navigate(item.ruta) {
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icono,
                        contentDescription = item.titulo
                    )
                },
                label = {
                    Text(
                        text = item.titulo,
                        fontWeight = if (seleccionado) FontWeight.Bold else FontWeight.Medium
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,

                    unselectedIconColor = Color(0xFFE3EEFF),
                    unselectedTextColor = Color(0xFFE3EEFF),

                    indicatorColor = Color(0xFF304FC4)
                )
            )
        }
    }
}