package com.example.appsemana03.presentation.screens.buscar

data class BuscarUiState(
    val nombres: String = "",
    val dni: String = "",
    val edad: String = "",
    val ciclo: String = "",
    val facultad: String = "",
    val carrera: String = "",
    val direccion: String = "",
    val correo: String = "",
    val linkedin: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)
