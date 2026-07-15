package com.example.appsemana03.presentation.screens.historial

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appsemana03.di.AppContainer

@Composable
fun HistorialScreen(container: AppContainer) {
    val viewModel = container.historialViewModel
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // ---------- Encabezado ----------
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFFEAEEFB))
                .padding(horizontal = 8.dp, vertical = 3.dp)
        ) {
            Text(
                text = "DEVICE HISTORY",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3949AB),
                fontFamily = FontFamily.Monospace,
                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = uiState.nombreEquipo,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0F172A),
            lineHeight = 30.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.QrCode2,
                contentDescription = null,
                tint = Color(0xFF9E9E9E),
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "ID: ${uiState.activoId}",
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace,
                color = Color(0xFF757575)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Badge de estado
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(Color(0xFF3B82F6))
                .padding(horizontal = 12.dp, vertical = 5.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = uiState.estadoEquipo,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ---------- Cabecera de la línea de tiempo + indicador de solo lectura ----------
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "LÍNEA DE TIEMPO",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF9E9E9E),
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFFEEEEEE))
                    .padding(horizontal = 8.dp, vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color(0xFF757575),
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Solo lectura",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF757575)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // ---------- Timeline (solo lectura, orden descendente) ----------
        uiState.eventos.forEachIndexed { index, evento ->
            EventoTimelineItem(
                evento = evento,
                isLast = index == uiState.eventos.lastIndex
            )
        }
    }
}

@Composable
private fun EventoTimelineItem(evento: EventoHistorial, isLast: Boolean) {
    val color = colorPorTipo(evento.tipo)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        // Columna izquierda: círculo + línea vertical
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(40.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(color),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = iconoPorTipo(evento.tipo),
                    contentDescription = evento.tipo,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .weight(1f)
                        .background(Color(0xFFE0E0E0))
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Card del evento
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = if (isLast) 0.dp else 20.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFEEEEEE)),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = evento.tipo,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = color
                    )
                    Text(
                        text = evento.fecha,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                        color = color
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Avatar(evento.responsable)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = evento.responsable,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF212121)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = evento.descripcion,
                    fontSize = 12.sp,
                    color = Color(0xFF475569),
                    lineHeight = 17.sp
                )
            }
        }
    }
}

@Composable
private fun Avatar(name: String) {
    val initials = name.substringAfter(":").trim()
        .split(" ")
        .filter { it.isNotBlank() }
        .take(2)
        .joinToString("") { it.first().uppercase() }

    Box(
        modifier = Modifier
            .size(22.dp)
            .clip(CircleShape)
            .background(Color(0xFFE0E0E0)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF616161)
        )
    }
}

private fun colorPorTipo(tipo: String): Color {
    return when (tipo) {
        "Devuelto" -> Color(0xFF1A237E)
        "Asignado" -> Color(0xFF3F51B5)
        "Reparación" -> Color(0xFFD32F2F)
        "Cambio de ubicación" -> Color(0xFF1E88E5)
        "Registrado" -> Color(0xFF757575)
        else -> Color(0xFF757575)
    }
}

private fun iconoPorTipo(tipo: String): ImageVector {
    return when (tipo) {
        "Devuelto" -> Icons.AutoMirrored.Filled.ArrowBack
        "Asignado" -> Icons.Default.Person
        "Reparación" -> Icons.Default.Build
        "Cambio de ubicación" -> Icons.Default.Place
        "Registrado" -> Icons.Default.Inventory2
        else -> Icons.Default.Inventory2
    }
}