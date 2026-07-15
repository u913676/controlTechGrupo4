package com.martinez.appitasset.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterForm(
    equipmentType: String,
    brand: String,
    model: String,
    serialNumber: String,
    assetTag: String,
    status: String,
    location: String,
    asignedUser: String,
    purchaseDate: String,
    notes: String,
    onEquipmentTypeChange: (String) -> Unit,
    onBrandChange: (String) -> Unit,
    onModelChange: (String) -> Unit,
    onSerialNumberChange: (String) -> Unit,
    onAssetTagChange: (String) -> Unit,
    onStatusChange: (String) -> Unit,
    onLocationChange: (String) -> Unit,
    onAsignedUserChange: (String) -> Unit,
    onPurchaseDateChange: (String) -> Unit,
    onNotesChange: (String) -> Unit
){

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Tipo de equipo", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = equipmentType,
            onValueChange = onEquipmentTypeChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Computers") }
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Marca", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = brand,
            onValueChange = onBrandChange,
            label = { Text("Marca") },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Ej: Dell, HP, Apple") },
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Modelo", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = model,
            onValueChange = onModelChange,
            label = { Text("Model") },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Ej: Precision 5570") },
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Número de serie", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = serialNumber,
            onValueChange = onSerialNumberChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("SN-XXXXXXX") },
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Código patrimonial", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = assetTag,
            onValueChange = onAssetTagChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("INV-2024-001") },
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Estado", fontWeight = FontWeight.Bold)
        var estado by remember { mutableStateOf("Nuevo") }
        EstadoDropdown(
            selected = estado,
            onSelectedChange = { estado = it },
            onStatusChange,
            status = status
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Ubicaciòn", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = location,
            onValueChange = onLocationChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Ej: Piso 4 - IT Lab") },
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Usuario Asignado", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = asignedUser,
            onValueChange = onAsignedUserChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Nombre o ID") },
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Fecha de compra", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = purchaseDate,
            onValueChange = onPurchaseDateChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("dd/mm/yyyy") },
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Observaciones", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = notes,
            onValueChange = onNotesChange,
            placeholder = { Text("Detalles adicionales, configuración especial...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp), // altura fija
            maxLines = 5
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstadoDropdown(
    selected: String,
    onSelectedChange: (String) -> Unit,
    onStateChange: (String) -> Unit,
    status: String
) {
    var expanded by remember { mutableStateOf(false) }
    onStateChange("Nuevo")
    val opciones = listOf("Nuevo", "En uso", "Dañado")
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selected,
            onValueChange = onStateChange,
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black,
            ),
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(opcion) },
                    onClick = {
                        onStateChange(opcion)
                        onSelectedChange(opcion)
                        expanded = false
                    }
                )
            }
        }
    }
}
