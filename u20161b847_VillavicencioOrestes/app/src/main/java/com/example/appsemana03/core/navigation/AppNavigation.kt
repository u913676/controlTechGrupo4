package com.example.appsemana03.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appsemana03.di.AppContainer
import com.example.appsemana03.presentation.screens.buscar.BuscarScreen
import com.example.appsemana03.presentation.screens.inicio.InicioScreen
import com.example.appsemana03.presentation.screens.listar.ListarScreen
import com.example.appsemana03.presentation.screens.agregar.AgregarScreen
import com.example.appsemana03.presentation.screens.asignar.AsignarScreen
import com.example.appsemana03.presentation.screens.historial.HistorialScreen

@Composable
fun AppNavigation(
    navHostController: NavHostController, container: AppContainer,
    paddingValues: PaddingValues
){
    NavHost(
        navController = navHostController,
        startDestination = NavRutas.INICIO,
        modifier = Modifier.padding(paddingValues)){

        composable(NavRutas.INICIO){
            InicioScreen()
        }
        composable(NavRutas.LISTAR){
            ListarScreen()
        }
        composable(NavRutas.AGREGAR){
            AgregarScreen(container)
        }
        composable(NavRutas.BUSCAR){
            BuscarScreen(container)
        }
        composable(NavRutas.ASIGNAR){
            AsignarScreen(container)
        }
        composable(NavRutas.HISTORIAL){
            HistorialScreen(container)
        }
    }
}

