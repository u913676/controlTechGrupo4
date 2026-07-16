package com.example.appsemana03.presentation.screens.historial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.appsemana03.di.AppContainer
import com.example.appsemana03.presentation.components.EquipoCard

// Lista de equipos del módulo Historial. Al tocar una card se abre su detalle.
@Composable
fun HistorialListaScreen(container: AppContainer, onVerDetalle: () -> Unit) {
    val viewModel = container.historialViewModel
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        uiState.equipos.forEach { equipo ->
            EquipoCard(
                equipo = equipo,
                onClick = {
                    viewModel.seleccionarEquipo(equipo)
                    onVerDetalle()
                }
            )
        }
    }
}
