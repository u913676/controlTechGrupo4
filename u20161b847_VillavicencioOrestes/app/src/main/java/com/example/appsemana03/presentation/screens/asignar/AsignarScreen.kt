package com.example.appsemana03.presentation.screens.asignar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Laptop
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appsemana03.di.AppContainer

@Composable
fun AsignarScreen(container: AppContainer, onAsignado: () -> Unit) {
    val viewModel = container.asignarViewModel
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Resumen del equipo",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A237E)
        )

        // ---------- Card del equipo ----------
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFECEFF1)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Laptop,
                        contentDescription = "",
                        tint = Color(0xFF37474F),
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    // Fila superior: estado (teal) + id (derecha)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = uiState.estadoEquipo.uppercase(),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF00897B),
                            letterSpacing = 0.5.sp
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = uiState.activoId,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF9E9E9E)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = uiState.nombreEquipo,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF212121)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = uiState.especificaciones,
                        fontSize = 12.sp,
                        color = Color(0xFF757575)
                    )
                }
            }
        }

        // ---------- Usuario o área destino ----------
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            SectionLabel("USUARIO O ÁREA DESTINO")

            OutlinedTextField(
                value = uiState.destinatario,
                onValueChange = viewModel::onDestinatarioChange,
                placeholder = { Text("Buscar por nombre, correo o departamento") },
                leadingIcon = { Icon(Icons.Default.PersonSearch, contentDescription = "") },
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AssistChip(
                    onClick = { viewModel.onDestinatarioChange("IT Department") },
                    label = { Text("IT Department") },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Business,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                )
                AssistChip(
                    onClick = { viewModel.onDestinatarioChange("Design Team") },
                    label = { Text("Design Team") },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                )
            }
        }

        // ---------- Fecha de entrega ----------
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            SectionLabel("FECHA DE ENTREGA")

            OutlinedTextField(
                value = uiState.fechaEntrega,
                onValueChange = viewModel::onFechaEntregaChange,
                placeholder = { Text("dd/mm/aaaa") },
                leadingIcon = { Icon(Icons.Default.CalendarMonth, contentDescription = "") },
                trailingIcon = {
                    Icon(Icons.Default.CalendarMonth, contentDescription = "Elegir fecha")
                },
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            )
        }

        // ---------- Observaciones ----------
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            SectionLabel("OBSERVACIONES ADICIONALES")

            OutlinedTextField(
                value = uiState.detalles,
                onValueChange = viewModel::onDetallesChange,
                placeholder = {
                    Text("Detalles sobre el estado de entrega, accesorios incluidos o requerimientos especiales...")
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(),
                minLines = 4
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = { viewModel.confirmarAsignacion(onExito = onAsignado) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1A237E),
                contentColor = Color(0xFFFFFFFF)
            )
        ) {
            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Confirmar asignación", fontWeight = FontWeight.Bold)
        }

        Text(
            text = "Al confirmar, se enviará un correo electrónico de notificación al destinatario.",
            fontSize = 11.sp,
            color = Color(0xFF9E9E9E),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF9E9E9E),
        letterSpacing = 1.sp
    )
}