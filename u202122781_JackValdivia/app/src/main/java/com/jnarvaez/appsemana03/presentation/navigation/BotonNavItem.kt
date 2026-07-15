package com.jnarvaez.appsemana03.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.History
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BotonNavItem(val ruta: String, val titulo: String, val icono: ImageVector) {
    data object Equipos : BotonNavItem(NavRutas.EQUIPOS, "Equipos", Icons.Default.Computer)
    data object Historial : BotonNavItem(NavRutas.HISTORIAL, "Historial", Icons.Default.History)
}
