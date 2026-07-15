package com.sanders.app_controltech.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanders.app_controltech.model.Equipo
import com.sanders.app_controltech.ui.theme.ControlTechBorder
import com.sanders.app_controltech.ui.theme.ControlTechCyan
import com.sanders.app_controltech.ui.theme.ControlTechLightBlue
import com.sanders.app_controltech.ui.theme.ControlTechTextSecondary
import com.sanders.app_controltech.ui.theme.StatusAssigned
import com.sanders.app_controltech.ui.theme.StatusAssignedBackground
import com.sanders.app_controltech.ui.theme.StatusAvailable
import com.sanders.app_controltech.ui.theme.StatusAvailableBackground
import com.sanders.app_controltech.ui.theme.StatusDisabled
import com.sanders.app_controltech.ui.theme.StatusDisabledBackground
import com.sanders.app_controltech.ui.theme.StatusRepair
import com.sanders.app_controltech.ui.theme.StatusRepairBackground

@Composable
fun EquipoCard(
    equipo: Equipo,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                EquipoIcono(
                    categoria = equipo.categoria
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = equipo.nombre,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "${equipo.marca} / ${equipo.modelo}",
                        fontSize = 12.sp,
                        color = ControlTechTextSecondary
                    )
                }

                EstadoEtiqueta(
                    estado = equipo.estado
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            InformacionEquipo(
                simbolo = "▣",
                texto = equipo.codigo
            )

            Spacer(modifier = Modifier.height(5.dp))

            InformacionEquipo(
                simbolo = "●",
                texto = equipo.ubicacion
            )

            Spacer(modifier = Modifier.height(5.dp))

            InformacionEquipo(
                simbolo = "♟",
                texto = equipo.responsable
            )
        }
    }
}

@Composable
private fun EquipoIcono(
    categoria: String
) {
    Box(
        modifier = Modifier
            .size(42.dp)
            .clip(RoundedCornerShape(9.dp)),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier.size(42.dp),
            shape = RoundedCornerShape(9.dp),
            color = ControlTechLightBlue
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = obtenerIconoCategoria(categoria),
                    fontSize = 20.sp,
                    color = ControlTechCyan
                )
            }
        }
    }
}

@Composable
private fun InformacionEquipo(
    simbolo: String,
    texto: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Text(
            text = simbolo,
            fontSize = 10.sp,
            color = ControlTechTextSecondary
        )

        Text(
            text = texto,
            fontSize = 12.sp,
            color = ControlTechTextSecondary
        )
    }
}

@Composable
private fun EstadoEtiqueta(
    estado: String
) {
    val colores = obtenerColoresEstado(estado)

    Surface(
        color = colores.second,
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = estado.uppercase(),
            modifier = Modifier.padding(
                horizontal = 9.dp,
                vertical = 4.dp
            ),
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            color = colores.first
        )
    }
}

private fun obtenerIconoCategoria(categoria: String): String {
    return when (categoria.lowercase()) {
        "laptop" -> "▰"
        "pc" -> "▣"
        "monitor" -> "▱"
        "impresora" -> "▤"
        "redes" -> "⌁"
        else -> "▣"
    }
}

private fun obtenerColoresEstado(
    estado: String
): Pair<Color, Color> {
    return when (estado.lowercase()) {
        "disponible" -> {
            StatusAvailable to StatusAvailableBackground
        }

        "asignado" -> {
            StatusAssigned to StatusAssignedBackground
        }

        "reparación", "reparacion" -> {
            StatusRepair to StatusRepairBackground
        }

        "de baja" -> {
            StatusDisabled to StatusDisabledBackground
        }

        else -> {
            ControlTechTextSecondary to ControlTechBorder
        }
    }
}