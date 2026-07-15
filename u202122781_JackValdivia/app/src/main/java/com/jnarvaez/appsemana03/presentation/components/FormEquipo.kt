package com.jnarvaez.appsemana03.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FormEquipo(
    equipo: String, usuarioAsignado: String, fechaEntrega: String, obs: String,
    onEquipoChange: (String) -> Unit, onUsuarioAsignadoChange: (String) -> Unit,
    onFechaEntregaChange: (String) -> Unit, onObsChange: (String) -> Unit
) {
    OutlinedTextField(
        value = equipo,
        onValueChange = onEquipoChange,
        label = { Text("Equipo") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = usuarioAsignado,
        onValueChange = onUsuarioAsignadoChange,
        label = { Text("Usuario Asignado") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = fechaEntrega,
        onValueChange = onFechaEntregaChange,
        label = { Text("Fecha de Entrega") },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = obs,
        onValueChange = onObsChange,
        label = { Text("Observaciones") },
        modifier = Modifier.fillMaxWidth(),
        minLines = 4
    )
}
