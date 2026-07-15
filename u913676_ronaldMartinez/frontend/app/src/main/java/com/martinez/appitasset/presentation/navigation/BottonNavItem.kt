package com.martinez.appitasset.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottonNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector) {

    data object home: BottonNavItem(
        route = NavRoutes.HOME,
        title = "Inicio",
        icon = Icons.Default.Home
    )

    data object itAssets: BottonNavItem(
        route = NavRoutes.ITASSETS,
        title = "Equipos",
        icon = Icons.Default.Home
    )

    data object register: BottonNavItem(
        route = NavRoutes.REGISTER,
        title = "Registrar",
        icon = Icons.Default.AppRegistration
    )

    data object history: BottonNavItem(
        route = NavRoutes.HISTORY,
        title = "Historial",
        icon = Icons.Default.History
    )

    data object profile: BottonNavItem(
        route = NavRoutes.PROFILE,
        title = "Perfil",
        icon = Icons.Default.VerifiedUser
    )
}