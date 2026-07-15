package com.example.appsemana03.presentation.screens.agregar

import androidx.compose.ui.text.font.FontLoadingStrategy

data class AgregarUiState(
    val titulo: String = "",
    val autor: String = "",
    val anio: String = "",
    val genero: String = "",
    val descripcion: String = "",
    val isLoading : Boolean = false,
    val isSuccess : Boolean = false,
    val errorMessage : String? = null
)
