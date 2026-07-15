package com.martinez.appitasset.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LaptopMac
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import com.martinez.appitasset.presentation.screens.view.ViewUIState

@Composable
fun ViewITAssetCard(viewModel: ViewUIState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally, // todo centrado
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Ícono grande
            Icon(
                imageVector = Icons.Default.LaptopMac,
                contentDescription = "${viewModel.brand} ${viewModel.model}",
                tint = Color(0xFF0D47A1),
                modifier = Modifier.size(64.dp)
            )

            // Asset ID
            Text(
                text = "ASSET ID: ${viewModel.serialNumber}",
                color = Color(0xFF0D47A1),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )

            // Nombre y especificaciones
            Text(
                text = "${viewModel.equipmentType} ${viewModel.model}",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = viewModel.assetTag,
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium
            )

            // Estado
            Text(
                text = viewModel.status,
                color = Color(0xFF1565C0),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )

            // Detalles
            Text(text = "Última auditoría: ${viewModel.purchaseDate}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Asignado a: ${viewModel.asignedUser}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Ubicación: ${viewModel.location}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Garantía: ${viewModel.notes}", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Costo: $3,499.00", // cámbialo si tienes un campo costo
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botones centrados abajo
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

            }
        }
    }
}
