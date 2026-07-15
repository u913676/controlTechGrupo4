package com.jnarvaez.appsemana03.presentation.screens.reparacion

data class ReparacionUiState(
    val id: String = "",
    val equipo: String = "",
    val usuarioAsignado: String = "",
    val fechaEntrega: String = "",
    val obs: String = "",
    val motivo: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val equipoNoDisponible: Boolean = false,
    val equipoEnviado: String = "",
    val motivoEnviado: String = "",
    val errorMessage: String? = null
)
