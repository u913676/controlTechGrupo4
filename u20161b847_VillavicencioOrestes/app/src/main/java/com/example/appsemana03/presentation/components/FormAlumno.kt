package com.example.appsemana03.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FormAlumno(
    nombres: String,
    dni: String,
    edad: String,
    ciclo: String,
    facultad: String,
    carrera: String,
    direccion: String,
    correo: String,
    linkedin: String,
    onNombresChange: (String) -> Unit,
    onDniChange: (String) -> Unit,
    onEdadChange: (String) -> Unit,
    onCicloChange: (String) -> Unit,
    onFacultadChange: (String) -> Unit,
    onCarreraChange: (String) -> Unit,
    onDireccionChange: (String) -> Unit,
    onCorreoChange: (String) -> Unit,
    onLinkedinChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = nombres,
        onValueChange = onNombresChange,
        label = { Text("Nombres y Apellidos") },
        modifier = Modifier.fillMaxWidth()
    )

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = dni,
            onValueChange = onDniChange,
            label = { Text("DNI") },
            modifier = Modifier.weight(1f)
        )
        OutlinedTextField(
            value = edad,
            onValueChange = onEdadChange,
            label = { Text("Edad") },
            modifier = Modifier.weight(1f)
        )
        OutlinedTextField(
            value = ciclo,
            onValueChange = onCicloChange,
            label = { Text("Ciclo") },
            modifier = Modifier.weight(1f)
        )
    }

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = facultad,
            onValueChange = onFacultadChange,
            label = { Text("Facultad") },
            modifier = Modifier.weight(1f)
        )
        OutlinedTextField(
            value = carrera,
            onValueChange = onCarreraChange,
            label = { Text("Carrera") },
            modifier = Modifier.weight(1f)
        )
    }

    OutlinedTextField(
        value = direccion,
        onValueChange = onDireccionChange,
        label = { Text("Dirección") },
        modifier = Modifier.fillMaxWidth()
    )

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = correo,
            onValueChange = onCorreoChange,
            label = { Text("Correo") },
            modifier = Modifier.weight(1f)
        )
        OutlinedTextField(
            value = linkedin,
            onValueChange = onLinkedinChange,
            label = { Text("LinkedIn") },
            modifier = Modifier.weight(1f)
        )
    }
}
