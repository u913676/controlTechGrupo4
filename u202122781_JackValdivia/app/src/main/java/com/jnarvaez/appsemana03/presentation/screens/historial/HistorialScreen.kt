package com.jnarvaez.appsemana03.presentation.screens.historial

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.jnarvaez.appsemana03.di.AppContainer
import com.jnarvaez.appsemana03.presentation.components.EmptyScreen

@Composable
fun HistorialScreen(container: AppContainer, navHostController: NavHostController) {
    val uiState by container.historialViewModel.uiState.collectAsState()

    if (uiState.eventos.isEmpty()) {
        EmptyScreen("No hay eventos recientes")
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(uiState.eventos) { item ->
                HistorialItem(item)
            }
        }
    }
}
