package com.jnarvaez.appsemana03.presentation.screens.editar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jnarvaez.appsemana03.di.AppContainer
import com.jnarvaez.appsemana03.presentation.components.FormEquipo
import com.jnarvaez.appsemana03.presentation.components.LoadingScreen
import com.jnarvaez.appsemana03.presentation.navigation.NavRutas

@Composable
fun EditarScreen(container: AppContainer, navHostController: NavHostController, id: String) {
    val viewModel = container.editarViewModel
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(id) {
        viewModel.buscarEquipo(id)
    }

    LaunchedEffect(uiState.equipoNoDisponible) {
        if (uiState.equipoNoDisponible) {
            navHostController.popBackStack()
        }
    }

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            container.equiposViewModel.cargarEquipos()

            if (uiState.huboReasignacion) {
                container.historialViewModel.agregarReasignacion(
                    uiState.equipoReasignado,
                    uiState.usuarioAnterior,
                    uiState.usuarioNuevo
                )
                navHostController.navigate(NavRutas.HISTORIAL) {
                    popUpTo(NavRutas.EQUIPOS)
                }
            } else {
                navHostController.popBackStack()
            }

            viewModel.clearSuccess()
        }
    }

    if (uiState.isLoading && uiState.id.isEmpty()) {
        LoadingScreen()
    } else {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FormEquipo(
                uiState.equipo, uiState.usuarioAsignado, uiState.fechaEntrega, uiState.obs,
                viewModel::onEquipoChange, viewModel::onUsuarioAsignadoChange,
                viewModel::onFechaEntregaChange, viewModel::onObsChange
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = !uiState.isLoading,
                onClick = { viewModel.actualizarEquipo() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1A237E),
                    contentColor = Color(0xFFFFFFFF)
                )
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Icon(Icons.Default.Save, "")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Actualizar Equipo")
                }
            }
        }
    }
}
