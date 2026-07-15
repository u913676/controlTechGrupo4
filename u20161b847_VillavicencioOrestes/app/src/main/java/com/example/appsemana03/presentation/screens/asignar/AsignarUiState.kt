package com.example.appsemana03.presentation.screens.asignar

data class AsignarUiState(
    val activoId: String = "CT-2024-8842",
    val nombreEquipo: String = "MacBook Pro 16\" - M2 Max",
    val especificaciones: String = "32GB RAM · 1TB SSD · Silver",
    val estadoEquipo: String = "Disponible",
    val destinatario: String = "",
    val fechaEntrega: String = "14/07/2026",
    val detalles: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)
