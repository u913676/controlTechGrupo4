package com.jnarvaez.appsemana03.presentation.screens.equipos

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.jnarvaez.appsemana03.di.AppContainer
import com.jnarvaez.appsemana03.domain.model.Equipo
import com.jnarvaez.appsemana03.presentation.components.ConfirmarDialog
import com.jnarvaez.appsemana03.presentation.components.EmptyScreen
import com.jnarvaez.appsemana03.presentation.components.LoadingScreen
import com.jnarvaez.appsemana03.presentation.navigation.NavRutas

@Composable
fun EquiposScreen(container: AppContainer, navHostController: NavHostController) {
    val viewModel = container.equiposViewModel
    val uiState by viewModel.uiState.collectAsState()
    var selectedEquipo by remember { mutableStateOf<Equipo?>(null) }

    if (uiState.isLoading) {
        LoadingScreen()
    } else {
        if (uiState.listaEquipos.isEmpty()) {
            EmptyScreen("No existen equipos registrados")
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(uiState.listaEquipos) { equipo ->
                    EquipoItem(
                        equipo = equipo,
                        onEdit = { navHostController.navigate("${NavRutas.EDITAR}/${equipo.id}") },
                        onRepair = { navHostController.navigate("${NavRutas.REPARACION}/${equipo.id}") },
                        onDelete = {
                            selectedEquipo = equipo
                            viewModel.setEliminar(true)
                        }
                    )
                }
            }
        }
    }

    if (uiState.isDelete) {
        ConfirmarDialog(
            titulo = "Eliminar Equipo",
            item = selectedEquipo?.equipo ?: "",
            onConfirm = {
                viewModel.eliminarEquipo(selectedEquipo!!.id)
                viewModel.setEliminar(false)
            },
            onDismiss = { viewModel.setEliminar(false) }
        )
    }
}
