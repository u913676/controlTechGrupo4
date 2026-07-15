package com.example.appsemana03.presentation.screens.agregar

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.appsemana03.di.AppContainer
import com.example.appsemana03.presentation.components.FormLibro

@Composable

fun AgregarScreen(container: AppContainer){
    val viewModel = container.agregarViewModel
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        FormLibro(
            uiState.titulo,uiState.autor, uiState.anio, uiState.genero,uiState.descripcion,
            viewModel :: onTituloChange,viewModel::onAutorChange,viewModel::onAnioChange,
            viewModel::onGeneroChange,viewModel::onDescripcionChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.guardarLibro()
            },
            colors = ButtonDefaults.buttonColors (
                containerColor = Color(0xFFE53967),
                contentColor = Color(0xFFFFFFFF)
            )
        ) {
            Icon(imageVector = Icons.Default.Save, contentDescription = "")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Guardar Libro")
        }
    }

}