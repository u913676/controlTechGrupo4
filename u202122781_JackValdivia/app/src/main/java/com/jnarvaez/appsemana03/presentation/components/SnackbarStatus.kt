package com.jnarvaez.appsemana03.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class SnackbarStatus(
    val colorFondo: Color,
    val colorTexto : Color,
    val icono : ImageVector
) {
    SUCCESS(Color(0xFF28DC2B), Color(0xFF000000), Icons.Default.CheckCircle),
    ERROR(Color(0xFFFA1402), Color(0xFFFFFFFF), Icons.Default.Error),
    WARNING(Color(0xFFFFE535), Color(0xFF000000), Icons.Default.Warning)
}