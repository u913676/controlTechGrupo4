package com.example.appsemana03.presentation.screens.asignar

import com.example.appsemana03.presentation.model.EquipoResumen

data class AsignarUiState(
    val activoId: String = "CT-2024-8842",
    val nombreEquipo: String = "MacBook Pro 16\" - M2 Max",
    val especificaciones: String = "32GB RAM · 1TB SSD · Silver",
    val estadoEquipo: String = "Disponible",
    val destinatario: String = "",
    val fechaEntrega: String = "",
    val detalles: String = "",
    val equipos: List<EquipoResumen> = emptyList(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)
