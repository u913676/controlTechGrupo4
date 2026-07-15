package com.martinez.appitasset.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LaptopMac
import androidx.compose.material.icons.filled.LocationOn
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
            .padding(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LaptopMac,
                    contentDescription = viewModel.brand + " " + viewModel.model,
                    tint = Color(0xFF0D47A1),
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = viewModel.status,
                    color = Color(0xFF2E7D32),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Text(
                text = viewModel.serialNumber,
                color = Color(0xFF0D47A1),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = viewModel.equipmentType + "|" + viewModel.model,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = viewModel.assetTag,
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Última auditoría: " + viewModel.purchaseDate,
                color = Color.DarkGray,
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "Asignado a: " + viewModel.asignedUser,
                style = MaterialTheme.typography.bodyMedium
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Ubicación",
                    tint = Color.Gray,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = viewModel.location,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Text(
                text = viewModel.assetTag,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = viewModel.notes,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}