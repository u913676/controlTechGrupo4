package com.example.appsemana03.presentation.screens.historial

import com.example.appsemana03.presentation.model.EquipoResumen

data class EventoHistorial(
    val tipo: String,
    val fecha: String,
    val fechaOrden: String, // ISO yyyy-MM-dd, usada para ordenar cronológicamente
    val responsable: String,
    val descripcion: String
)

data class HistorialUiState(
    val activoId: String = "ASSET-7729-PRC",
    val nombreEquipo: String = "Workstation Dell Precision",
    val estadoEquipo: String = "Operativo",
    val equipos: List<EquipoResumen> = emptyList(),
    val eventos: List<EventoHistorial> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
