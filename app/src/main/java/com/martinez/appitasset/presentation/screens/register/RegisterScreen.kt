package com.martinez.appitasset.presentation.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import com.martinez.appitasset.di.AppContainer
import com.martinez.appitasset.presentation.components.RegisterForm


@Composable
fun RegisterScreen(container: AppContainer) {
    val viewModel = container.registerViewModel
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Registrar equipo",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0D47A1),
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )
        Text(
            text = "Complete los detalles técnicos para añadir una nueva unidad al inventario global.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp)
        )

        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp) // separación entre componentes
            ) {
                RegisterForm(
                    equipmentType = uiState.equipmentType,
                    brand = uiState.brand,
                    model = uiState.model,
                    serialNumber = uiState.serialNumber,
                    assetTag = uiState.assetTag,
                    status = uiState.status,
                    location = uiState.location,
                    asignedUser = uiState.asignedUser,
                    purchaseDate = uiState.purchaseDate,
                    notes = uiState.notes,
                    viewModel::onEquipmentTypeChange,
                    viewModel::onBrandChange,
                    viewModel::onModelChange,
                    viewModel::onSerialNumberChange,
                    viewModel::onAssetTagChange,
                    viewModel::onStatusChange,
                    viewModel::onLocationChange,
                    viewModel::onAsignedUserChange,
                    viewModel::onPurchaseDateChange,
                    viewModel::onNotesChange
                )
            }
            Spacer(Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0D47A1), // azul oscuro
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp
                ),
                onClick = {
                    viewModel.onSaveData()
                }
            ) {
                Icon(
                    Icons.Default.Save,
                    contentDescription = "Guardar",
                    tint = Color.White
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "Guardar equipo",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
