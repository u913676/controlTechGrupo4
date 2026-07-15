package com.martinez.appitasset.presentation.components

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals

data class SnackbarCustom(
    override val actionLabel: String? = null,
    override val duration: SnackbarDuration = SnackbarDuration.Indefinite,
    override val message: String,
    override val withDismissAction: Boolean = false,
    val status: SnackbarStatus
): SnackbarVisuals
