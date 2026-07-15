package com.example.appsemana03.presentation.screens.buscar

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appsemana03.di.AppContainer
import com.example.appsemana03.presentation.components.FormAlumno

@Composable
fun BuscarScreen(container: AppContainer) {
    val viewModel = container.buscarViewModel
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Formulario Alumno",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        FormAlumno(
            nombres = uiState.nombres,
            dni = uiState.dni,
            edad = uiState.edad,
            ciclo = uiState.ciclo,
            facultad = uiState.facultad,
            carrera = uiState.carrera,
            direccion = uiState.direccion,
            correo = uiState.correo,
            linkedin = uiState.linkedin,
            onNombresChange = viewModel::onNombresChange,
            onDniChange = viewModel::onDniChange,
            onEdadChange = viewModel::onEdadChange,
            onCicloChange = viewModel::onCicloChange,
            onFacultadChange = viewModel::onFacultadChange,
            onCarreraChange = viewModel::onCarreraChange,
            onDireccionChange = viewModel::onDireccionChange,
            onCorreoChange = viewModel::onCorreoChange,
            onLinkedinChange = viewModel::onLinkedinChange
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.guardarAlumno() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE53967),
                contentColor = Color(0xFFFFFFFF)
            )
        ) {
            Icon(imageVector = Icons.Default.Save, contentDescription = "")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Guardar Alumno")
        }
    }
}
