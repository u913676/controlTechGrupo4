package com.jnarvaez.appsemana03.presentation.screens.editar

data class EditarUiState(
    val id: String = "",
    val equipo: String = "",
    val usuarioAsignado: String = "",
    val usuarioAsignadoOriginal: String = "",
    val fechaEntrega: String = "",
    val obs: String = "",
    val estado: String = "",
    val motivoReparacion: String? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val huboReasignacion: Boolean = false,
    val equipoReasignado: String = "",
    val usuarioAnterior: String = "",
    val usuarioNuevo: String = "",
    val equipoNoDisponible: Boolean = false,
    val errorMessage: String? = null
)
