package com.jnarvaez.appsemana03.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jnarvaez.appsemana03.di.AppContainer
import com.jnarvaez.appsemana03.presentation.screens.editar.EditarScreen
import com.jnarvaez.appsemana03.presentation.screens.equipos.EquiposScreen
import com.jnarvaez.appsemana03.presentation.screens.historial.HistorialScreen
import com.jnarvaez.appsemana03.presentation.screens.reparacion.ReparacionScreen

@Composable
fun AppNavigation(navHostController: NavHostController, container: AppContainer, paddingValues: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = NavRutas.EQUIPOS,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(NavRutas.EQUIPOS) {
            EquiposScreen(container, navHostController)
        }
        composable(NavRutas.HISTORIAL) {
            HistorialScreen(container, navHostController)
        }
        composable("${NavRutas.EDITAR}/{id}") { entrada ->
            val id = entrada.arguments?.getString("id") ?: ""
            EditarScreen(container, navHostController, id)
        }
        composable("${NavRutas.REPARACION}/{id}") { entrada ->
            val id = entrada.arguments?.getString("id") ?: ""
            ReparacionScreen(container, navHostController, id)
        }
    }
}
