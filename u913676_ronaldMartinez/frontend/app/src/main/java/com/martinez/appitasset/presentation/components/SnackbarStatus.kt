package com.martinez.appitasset.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class SnackbarStatus(
    val backgroundColor: Color,
    val textColor: Color,
    val icon: ImageVector
) {
    SUCCESS(
        Color(0xFF08F440),
        textColor = Color(0xFF1A1818),
        icon = Icons.Default.CheckCircle
    ),

    ERROR(
        Color(0xFFB40B06),
        textColor = Color(0xFFEAE5E5),
        icon = Icons.Default.Error
    ),

    WARNING(
        Color(0xFFB4F306),
        textColor = Color(0xFF211F1F),
        icon = Icons.Default.Warning
    )

}