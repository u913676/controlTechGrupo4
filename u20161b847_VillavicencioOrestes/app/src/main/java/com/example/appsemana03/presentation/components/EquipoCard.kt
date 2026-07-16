package com.example.appsemana03.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DesktopWindows
import androidx.compose.material.icons.filled.Laptop
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appsemana03.presentation.model.EquipoResumen
import com.example.appsemana03.presentation.model.TipoEquipo

// Card visual de un equipo. Se usa tanto en la lista de Historial como en la de Asignar.
@Composable
fun EquipoCard(equipo: EquipoResumen, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFEEEEEE)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Fila superior: ícono del equipo + badge de estado
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFE8F1FE)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = iconoPorTipo(equipo.tipo),
                        contentDescription = null,
                        tint = Color(0xFF1E88E5),
                        modifier = Modifier.size(24.dp)
                    )
                }
                EstadoBadge(equipo.estado)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = equipo.nombre,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF212121)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = equipo.modelo,
                fontSize = 13.sp,
                color = Color(0xFF757575)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // ID
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.QrCode2,
                    contentDescription = null,
                    tint = Color(0xFF9E9E9E),
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = equipo.id,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color(0xFF616161)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Ubicación o responsable
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = if (equipo.estado == "Disponible") Icons.Default.Place else Icons.Default.Person,
                    contentDescription = null,
                    tint = Color(0xFF9E9E9E),
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = equipo.detalle,
                    fontSize = 12.sp,
                    color = Color(0xFF616161)
                )
            }
        }
    }
}

@Composable
private fun EstadoBadge(estado: String) {
    val (fondo, texto) = coloresEstado(estado)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(fondo)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = estado.uppercase(),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = texto,
            letterSpacing = 0.5.sp
        )
    }
}

private fun coloresEstado(estado: String): Pair<Color, Color> {
    return when (estado) {
        "Disponible" -> Color(0xFFDCF3E4) to Color(0xFF2E7D32)
        "Asignado" -> Color(0xFFE7EAFB) to Color(0xFF3949AB)
        "Reparación" -> Color(0xFFFDECC8) to Color(0xFFB26A00)
        "Devuelto" -> Color(0xFFECEFF1) to Color(0xFF455A64)
        else -> Color(0xFFEEEEEE) to Color(0xFF757575)
    }
}

private fun iconoPorTipo(tipo: TipoEquipo): ImageVector {
    return when (tipo) {
        TipoEquipo.LAPTOP -> Icons.Default.Laptop
        TipoEquipo.WORKSTATION -> Icons.Default.DesktopWindows
        TipoEquipo.IMPRESORA -> Icons.Default.Print
    }
}
