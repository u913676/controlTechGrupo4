package com.example.appsemana03.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentInd
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BotonNavItem(val ruta:String, val titulo: String, val icono: ImageVector) {
    data object Inicio: BotonNavItem(NavRutas.INICIO, titulo = "Inicio", Icons.Default.Home)
    data object Listar: BotonNavItem(NavRutas.AGREGAR, titulo = "Agregar", Icons.Default.PlusOne)
    data object Buscar: BotonNavItem(NavRutas.BUSCAR, titulo = "Inicio", Icons.Default.Search)
    data object Asignar: BotonNavItem(NavRutas.ASIGNAR, titulo = "Asignar", Icons.Default.AssignmentInd)
    data object Historial: BotonNavItem(NavRutas.HISTORIAL, titulo = "Historial", Icons.Default.History)
}
